package com.sidc.sits.logical.shopping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopItemLangBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopPhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingItemInsertRequest;
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

public class ShoppingItemInsertProcess extends AbstractAuthAPIProcess {

	private final ShoppingItemInsertRequest entity;
	private final String STEP = "2";

	public ShoppingItemInsertProcess(final ShoppingItemInsertRequest entity) {
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
		final int id = ShopManager.getInstance().insertItem(entity.getCategoryid(), entity.getVendorid(),
				entity.getStatus(), entity.getSequence(), entity.getPrice(), entity.getSellingprice(), entity.getQty(),
				entity.getWeight(), entity.getList(), entity.getPhotolist());
		LogAction.getInstance().debug("step 1/" + STEP + ":insert success(ShopManager|insertItem).");

		final List<String> folderList = Arrays.asList(String.valueOf(id));

		List<PhotoUploadBean> photoList = new ArrayList<PhotoUploadBean>();
		for (final ShopPhotoUploadBean photoEntity : entity.getPhotolist()) {
			photoList.add(new PhotoUploadBean(photoEntity.getPhoto(),
					photoEntity.getName() + "." + photoEntity.getExtension()));
		}

		if (!photoList.isEmpty()) {
			// 通知 sits 上傳圖片
			try {
				final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());

				HttpClientUtils.sendPostWithUploadPhoto(sitsUrl + PageList.UPLOAD_PHOTO, folderList,
						PhotoType.SHOPITEM.toString(), photoList);
			} catch (Exception e) {
				// 備份
				LogAction.getInstance().error("photo upload fail !!", e);
				for (final ShopPhotoUploadBean photoEntity : entity.getPhotolist()) {
					PhotoManager.getInstance().updateWithBackup(String.valueOf(id), "2", photoEntity.getPhoto());
				}
				LogAction.getInstance().warn("file insert to database success.");
			}
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :upload photo success.");

		return id;
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
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status).");
		}
		if (entity.getCategoryid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(category id).");
		}
		if (entity.getVendorid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(vendor id).");
		}
		if (entity.getQty() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(qty).");
		}
		if (entity.getSequence() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(sequence).");
		}
		if (entity.getPrice() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(price).");
		}
		if (entity.getWeight() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(weight).");
		}
		if (entity.getSellingprice() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(selling price).");
		}
		/*
		 * if (entity.getPrice() > entity.getSellingprice()) { throw new
		 * SiDCException(APIStatus.ILLEGAL_ARGUMENT,
		 * "request illegal(price > selling price)."); }
		 */
		if (entity.getList() == null || entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(list).");
		}
		for (final ShopItemLangBean langEntity : entity.getList()) {
			if (StringUtils.isBlank(langEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(name).");
			}
			if (StringUtils.isBlank(langEntity.getLangcode())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code).");
			}
			if (langEntity.getName().length() > 100) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(name length).");
			}
			if (langEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code length).");
			}
			if (!StringUtils.isBlank(langEntity.getDescription()) && langEntity.getDescription().length() > 2048) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(description length).");
			}
			if (!StringUtils.isBlank(langEntity.getIntroduction()) && langEntity.getIntroduction().length() > 2048) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(introduction length).");
			}
		}
		if (entity.getPhotolist() != null) {
			final int max = Integer.valueOf(
					SystemPropertiesManager.getInstance().findPropertiesMessage("upload.image.capacity.maximum"));

			for (final ShopPhotoUploadBean photoEntity : entity.getPhotolist()) {
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
			entity.setPhotolist(new ArrayList<ShopPhotoUploadBean>());
		}
		ShopManager.getInstance().itemInsertCheck(entity.getCategoryid(), entity.getVendorid());
	}
}
