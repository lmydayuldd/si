package com.sidc.sits.logical.laundryservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;
import com.sidc.blackcore.api.mobile.laundry.request.LaundryTypeUpdateRequest;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.LaundryServiceManager;
import com.sidc.dao.sits.manager.PhotoManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class LaundryTypeUpdateProcess extends AbstractAuthAPIProcess {

	private final LaundryTypeUpdateRequest entity;
	private final String STEP = "1";
	private List<String> photoNameList = new ArrayList<String>();

	public LaundryTypeUpdateProcess(final LaundryTypeUpdateRequest entity) {
		// TODO Auto-generated constructor stub
		super(entity.getToken());
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// 先記錄要刪的資料
		if (entity.isPhotoupdate()) {
			photoNameList = PhotoManager.getInstance().seleceNameById(String.valueOf(entity.getTypeid()),
					PhotoType.LAUNDRYTYPE.toString());
		}

		LaundryServiceManager.getInstance().updateType(entity.getTypeid(), entity.getStatus(), entity.getList(),
				entity.isPhotoupdate(), entity.getPhotolist());
		LogAction.getInstance().debug("step 1/" + STEP + " :update success(LaundryServiceManager|updateType).");

		if (entity.isPhotoupdate()) {
			final List<String> folderList = Arrays.asList(String.valueOf(entity.getTypeid()));

			List<PhotoUploadBean> photoList = new ArrayList<PhotoUploadBean>();
			for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
				photoList.add(new PhotoUploadBean(photoEntity.getPhoto(),
						photoEntity.getName() + "." + photoEntity.getExtension()));
			}

			if (!photoList.isEmpty()) {
				final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
				// 通知 sits 上傳圖片
				try {
					HttpClientUtils.sendPostWithUploadPhoto(sitsUrl + PageList.UPLOAD_PHOTO, folderList,
							PhotoType.LAUNDRYTYPE.toString(), photoList);

					LogAction.getInstance().debug("upload success.");
				} catch (Exception e) {
					// 備份
					LogAction.getInstance().error("photo upload fail !!", e);
					for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
						PhotoManager.getInstance().updateWithBackup(String.valueOf(entity.getTypeid()), "2",
								photoEntity.getPhoto());
					}
					LogAction.getInstance().warn("file insert to database success.");
				}

				// 通知 sits 把舊圖刪了
				try {
					HttpClientUtils.sendPostWithDeletePhoto(sitsUrl + PageList.DELETE_PHOTO, folderList,
							PhotoType.LAUNDRYTYPE.toString(), photoNameList);

					LogAction.getInstance().debug("delete success.");
				} catch (Exception e) {
					LogAction.getInstance().error("photo delete fail !!", e);
				}
			}
		}

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getTypeid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of type id.");
		}
		if (entity.getStatus() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of list.");
		}
		for (final LaundryLangBean langEntity : entity.getList()) {
			if (StringUtils.isBlank(langEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name.");
			}
			if (StringUtils.isBlank(langEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode.");
			}
			if (langEntity.getName().length() > 25) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of name(length).");
			}
			if (langEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode(lang code).");
			}
			if (!StringUtils.isBlank(langEntity.getDescription()) && langEntity.getDescription().length() > 1024) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of langecode(description).");
			}
		}
		if (!entity.isPhotoupdate()) {
			entity.setPhotolist(null);
		}
		if (entity.getPhotolist() != null) {
			final int max = Integer.valueOf(
					SystemPropertiesManager.getInstance().findPropertiesMessage("upload.image.capacity.maximum"));

			for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
				final int photoLength = photoEntity.getPhoto().length;
				if (photoEntity.getPhoto() == null || photoLength <= 0) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo).");
				}
				if (photoLength / 1024 > max) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo over limit size).");
				}
				if (StringUtils.isBlank(photoEntity.getName())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo name).");
				}
				if (StringUtils.isBlank(photoEntity.getExtension())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(photo extension).");
				}
			}
		} else {
			entity.setPhotolist(new ArrayList<ActivityPhotoUploadBean>());
		}
		if (!LaundryServiceManager.getInstance().isExistOfTypeId(entity.getTypeid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find type id.");
		}
	}
}
