package com.sidc.sits.logical.laundryservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToReturnTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToWashTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;
import com.sidc.blackcore.api.mobile.laundry.request.LaundryItemInsertRequest;
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
public class LaundryItemInsertProcess extends AbstractAuthAPIProcess {

	private final LaundryItemInsertRequest entity;
	private final String STEP = "2";

	public LaundryItemInsertProcess(final LaundryItemInsertRequest entity) {
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

		final int id = LaundryServiceManager.getInstance().insertItem(entity.getStatus(), entity.getType(),
				entity.getList(), entity.getWashtypelist(), entity.getReturntypelist(), entity.getPhotolist());
		LogAction.getInstance().debug("step 1/" + STEP + " :insert success(LaundryServiceManager|insertItem).");

		final List<String> folderList = Arrays.asList(String.valueOf(id));

		List<PhotoUploadBean> photoList = new ArrayList<PhotoUploadBean>();
		for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
			photoList.add(new PhotoUploadBean(photoEntity.getPhoto(),
					photoEntity.getName() + "." + photoEntity.getExtension()));
		}
		if (!photoList.isEmpty()) {
			// 通知 sits 上傳圖片
			try {
				final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());

				HttpClientUtils.sendPostWithUploadPhoto(sitsUrl + PageList.UPLOAD_PHOTO, folderList,
						PhotoType.LAUNDRYITEM.toString(), photoList);
			} catch (Exception e) {
				// 備份
				LogAction.getInstance().error("photo upload fail !!", e);
				for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
					PhotoManager.getInstance().updateWithBackup(String.valueOf(id), "2", photoEntity.getPhoto());
				}
				LogAction.getInstance().warn("file insert to database success.");
			}
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :upload success.");

		return id;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getStatus() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (entity.getType() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of list.");
		}
		if (entity.getWashtypelist() == null || entity.getWashtypelist().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of wash type list.");
		}
		if (entity.getReturntypelist() == null || entity.getReturntypelist().isEmpty()) {
			entity.setReturntypelist(new ArrayList<LaundryItemToReturnTypeBean>());
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
		List<Integer> washTypeIdList = new ArrayList<Integer>();
		for (final LaundryItemToWashTypeBean washTypeEntity : entity.getWashtypelist()) {
			if (washTypeEntity.getWashtypeid() < 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of wash type id.");
			}
			if (washTypeEntity.getPrice() < 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of price.");
			}
			washTypeIdList.add(washTypeEntity.getWashtypeid());
		}
		List<Integer> returnTypeIdList = new ArrayList<Integer>();
		for (final LaundryItemToReturnTypeBean returnTypeEntity : entity.getReturntypelist()) {
			if (returnTypeEntity.getReturntypeid() < 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of return type id.");
			}
			returnTypeIdList.add(returnTypeEntity.getReturntypeid());
		}
		LaundryServiceManager.getInstance().itemInsertCheck(entity.getType(), returnTypeIdList, washTypeIdList);
	}
}
