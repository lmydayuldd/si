package com.sidc.sits.logical.roomservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCategoryUpdateRequest;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.PhotoManager;
import com.sidc.dao.sits.manager.RoomServiceManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RoomServiceCategoryUpdateProcess extends AbstractAuthAPIProcess {

	private final RoomServiceCategoryUpdateRequest entity;
	private final String STEP = "2";

	public RoomServiceCategoryUpdateProcess(final RoomServiceCategoryUpdateRequest entity) {
		super(entity.getToken());
		this.entity = entity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		// 先記錄要刪的資料
		List<String> photoNameList = PhotoManager.getInstance().seleceNameById(String.valueOf(entity.getId()),
				PhotoType.ROOMSERVICECATEGORY.toString());

		RoomServiceManager.getInstance().updateCategory(entity.getId(), entity.getStatus(), entity.getReferid(),
				entity.getStartime(), entity.getEndtime(), entity.getSequence(), entity.getList(),
				entity.isPhotoupdate(), entity.getPhotolist());
		LogAction.getInstance().debug("step 1/" + STEP + ":update success(RoomServiceManager|updateCategory).");

		List<String> folderList = new ArrayList<String>();
		folderList.add(String.valueOf(entity.getId()));

		List<PhotoUploadBean> photoList = new ArrayList<PhotoUploadBean>();
		for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
			photoList.add(new PhotoUploadBean(photoEntity.getPhoto(),
					photoEntity.getName() + "." + photoEntity.getExtension()));
		}

		if (entity.isPhotoupdate() && !photoList.isEmpty()) {
			final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
			// 通知 sits 上傳圖片
			try {
				HttpClientUtils.sendPostWithUploadPhoto(sitsUrl + PageList.UPLOAD_PHOTO, folderList,
						PhotoType.ROOMSERVICECATEGORY.toString(), photoList);

				LogAction.getInstance().debug("upload success.");
			} catch (Exception e) {
				// 備份
				LogAction.getInstance().error("photo upload fail !!", e);
				for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
					PhotoManager.getInstance().updateWithBackup(String.valueOf(entity.getId()), "2",
							photoEntity.getPhoto());
				}
				LogAction.getInstance().warn("file insert to database success.");
			}

			// 通知 sits 把舊圖刪了
			try {
				folderList = new ArrayList<String>();
				folderList.add(String.valueOf(entity.getId()));

				HttpClientUtils.sendPostWithDeletePhoto(sitsUrl + PageList.DELETE_PHOTO, folderList,
						PhotoType.ROOMSERVICECATEGORY.toString(), photoNameList);

				LogAction.getInstance().debug("delete success.");
			} catch (Exception e) {
				LogAction.getInstance().error("photo delete fail !!", e);
			}
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :photo process success.");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (entity.getId() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(id).");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(token).");
		}
		if (!StringUtils.isBlank(entity.getStartime())) {
			if (entity.getStartime().length() > 20) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(star time length).");
			}
		}
		if (!StringUtils.isBlank(entity.getEndtime())) {
			if (entity.getEndtime().length() > 20) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(end time length).");
			}
		}
		if (entity.getReferid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(refer id).");
		}
		if (entity.getSequence() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(sequence).");
		}
		if (entity.getStatus() < 0 || entity.getStatus() > 1) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status).");
		}
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(list).");
		}
		for (final RoomServiceLangBean langEntity : entity.getList()) {
			if (StringUtils.isBlank(langEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code).");
			}
			if (StringUtils.isBlank(langEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(name).");
			}
			if (langEntity.getName().length() > 100) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(name length).");
			}
			if (langEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code length).");
			}
			if (!StringUtils.isBlank(langEntity.getDescription())) {
				if (langEntity.getDescription().length() > 2048) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(description length).");
				}
			}
		}
		if (!entity.isPhotoupdate()) {
			entity.setPhotolist(new ArrayList<ActivityPhotoUploadBean>());
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
		
		RoomServiceManager.getInstance().categoryupdateCheck(entity.getId(), entity.getReferid());

	}
}
