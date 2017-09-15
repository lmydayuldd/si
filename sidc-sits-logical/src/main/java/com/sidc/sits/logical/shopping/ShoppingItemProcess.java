package com.sidc.sits.logical.shopping;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingItemRequest;
import com.sidc.blackcore.api.sits.shop.response.ShoppingItemResponse;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ShoppingItemProcess extends AbstractAPIProcess {
	private final ShoppingItemRequest entity;
	private final String STEP = "3";

	public ShoppingItemProcess(final ShoppingItemRequest entity) {
		// TODO Auto-generated constructor stub
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		String status = null;
		String langCode = null;

		switch (entity.getStatus()) {
		case "enable":
			status = "1";
			break;
		case "disable":
			status = "0";
			break;
		case "sns":
			status = "2";
			break;
		case "all":
			break;
		}

		switch (entity.getLangcode()) {
		case "all":
			break;
		default:
			langCode = entity.getLangcode();
			break;
		}

		List<ShoppingItemResponse> list = ShopManager.getInstance().selectItem(entity.getItemid(),
				entity.getCategoryid(), status, entity.getVendorid(), langCode);
		LogAction.getInstance().debug("step 1/" + STEP + " :select success(ShopManager|selectItem).");

		// 取參數
		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		final String photoUrl = SystemPropertiesManager.getInstance().findPropertiesMessage("upload.shop.path")
				+ PhotoType.SHOPITEM.toString();
		LogAction.getInstance().debug("step 2/" + STEP + " :get system url success.");

		for (ShoppingItemResponse responseEntity : list) {
			responseEntity.setPhotolist(photoProcess(responseEntity.getPhotolist(), imageUrl, photoUrl));
			/*
			switch (responseEntity.getStatus()) {
			case "1":
				responseEntity.setStatus("enable");
				break;
			case "0":
				responseEntity.setStatus("disable");
				break;
			case "2":
				responseEntity.setStatus("sns");
				break;
			}
			*/
		}
		LogAction.getInstance().debug("step 3/" + STEP + ":transform status success.");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getCategoryid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(category id).");
		}
		if (entity.getVendorid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(vendor id).");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langcode).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(status).");
		}
	}

	/**
	 * 加上url
	 * 
	 * @param photoList
	 * @param sitsUrl
	 * @param photoUrl
	 * @return
	 */
	private List<ActivityPhotoBean> photoProcess(List<ActivityPhotoBean> photoList, final String sitsUrl,
			final String photoUrl) {
		for (ActivityPhotoBean photoEntity : photoList) {
			final String url = photoEntity.getPhoto();
			photoEntity.setPhoto(sitsUrl + photoUrl + url);
		}
		return photoList;
	}

}
