package com.sidc.sits.logical.shopping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingCategoryUpdateRequest;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.PhotoManager;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ShoppingCategoryUpdateProcess extends AbstractAuthAPIProcess {

	private final ShoppingCategoryUpdateRequest entity;
	private final String STEP = "1";

	public ShoppingCategoryUpdateProcess(final ShoppingCategoryUpdateRequest entity) {
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
		final List<String> photoNameList = PhotoManager.getInstance()
				.seleceNameById(String.valueOf(entity.getCategoryid()), PhotoType.SHOPCATEGORY.toString());

		ShopManager.getInstance().updateCategory(entity.getCategoryid(), entity.getStatus(), entity.getSequence(),
				entity.getReferid(), entity.getList(), entity.isPhotoupdate(), entity.getPhotolist());
		LogAction.getInstance().debug("step 1/" + STEP + ":insert success(ShopManager|updateCategory).");

		final List<String> folderList = Arrays.asList(String.valueOf(entity.getCategoryid()));

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
						PhotoType.SHOPCATEGORY.toString(), photoList);

				LogAction.getInstance().debug("upload success.");
			} catch (Exception e) {
				// 備份
				LogAction.getInstance().error("photo upload fail !!", e);
				for (final ActivityPhotoUploadBean photoEntity : entity.getPhotolist()) {
					PhotoManager.getInstance().updateWithBackup(String.valueOf(entity.getCategoryid()), "2",
							photoEntity.getPhoto());
				}
				LogAction.getInstance().warn("file insert to database success.");
			}

			// 通知 sits 把舊圖刪了
			try {
				HttpClientUtils.sendPostWithDeletePhoto(sitsUrl + PageList.DELETE_PHOTO, folderList,
						PhotoType.SHOPCATEGORY.toString(), photoNameList);

				LogAction.getInstance().debug("delete success.");
			} catch (Exception e) {
				LogAction.getInstance().error("photo delete fail !!", e);
			}
		}

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(token).");
		}
		if (entity.getCategoryid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(category id).");
		}
		if (entity.getStatus() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status).");
		}
		if (entity.getReferid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(refer id).");
		}
		if (entity.getSequence() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(sequence).");
		}
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(list).");
		}
		for (final ShopLangBean langEntity : entity.getList()) {
			if (StringUtils.isBlank(langEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(name).");
			}
			if (StringUtils.isBlank(langEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code).");
			}
			if (langEntity.getName().length() > 30) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(name length).");
			}
			if (langEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code length).");
			}
			if (!StringUtils.isBlank(langEntity.getDescription()) && langEntity.getDescription().length() > 1024) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(description length).");
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
		if (!ShopManager.getInstance().isExistByCategoryId(entity.getCategoryid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find category id).");
		}
		if (entity.getReferid() > 0 && !ShopManager.getInstance().isExistByCategoryReferId(entity.getReferid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find refer id).");
		}
	}
}
