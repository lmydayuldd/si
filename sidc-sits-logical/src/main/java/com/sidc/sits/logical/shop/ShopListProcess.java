package com.sidc.sits.logical.shop;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.bean.ShopItemInfoBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopTypeInfoBean;
import com.sidc.blackcore.api.sits.shop.request.ShopListRequest;
import com.sidc.blackcore.api.sits.shop.response.ShopListResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ShopListProcess extends AbstractAPIProcess {
	private ShopListRequest entity;

	public ShopListProcess(final ShopListRequest entity) {
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
		// TODO Auto-generated method stub

		final List<ShopTypeInfoBean> shopList = ShopManager.getInstance().selectShop(entity.getLangcode());
		LogAction.getInstance().debug("step 1/3: get shop list success(ShopManager|selectShop).");

		final String imgUrl = UrlUtils.getUrl(SidcUrlName.SHOPIMAGE.toString()) + "/";

		for (ShopTypeInfoBean shopInfoEntity : shopList) {
			for (final ShopItemInfoBean itemInfoEntity : shopInfoEntity.getShoppinglist()) {
				itemInfoEntity.setImage(imgUrl + itemInfoEntity.getImage());
			}
		}
		LogAction.getInstance().debug("step 2/3: update full image link success.");

		final String currency = SystemPropertiesManager.getInstance().findPropertiesMessage("CURRENCY");
		LogAction.getInstance()
				.debug("step 3/3: get shop currency success(SystemPropertiesManager|findPropertiesMessage|CURRENCY).");

		ShopListResponse response = new ShopListResponse(currency, shopList);

		return response;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getHotelid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(hotel id).");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(lang code).");
		}
	}
}
