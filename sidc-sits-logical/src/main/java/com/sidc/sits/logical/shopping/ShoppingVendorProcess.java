package com.sidc.sits.logical.shopping;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.request.ShoppingVendorRequest;
import com.sidc.blackcore.api.sits.shop.response.ShoppingVendorResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class ShoppingVendorProcess extends AbstractAPIProcess {
	private final ShoppingVendorRequest entity;
	private final String STEP = "2";

	public ShoppingVendorProcess(final ShoppingVendorRequest entity) {
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

		int status = -1;
		String langCode = null;

		switch (entity.getStatus()) {
		case "enable":
			status = 1;
			break;
		case "disable":
			status = 0;
			break;
		}

		switch (entity.getLangcode()) {
		case "all":
			break;
		default:
			langCode = entity.getLangcode();
			break;
		}
		LogAction.getInstance().debug("step 1/" + STEP + " :format request parameter success.");

		List<ShoppingVendorResponse> list = new ArrayList<ShoppingVendorResponse>();

		if (StringUtils.isBlank(entity.getVendorname())) {
			list = ShopManager.getInstance().selectVendor(entity.getVendorid(), status, langCode);
		} else {
			list = ShopManager.getInstance().selectVendor(entity.getVendorid(), status, entity.getVendorname(),
					langCode);
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :select success(ShopManager|selectItem).");

		/*
		for (ShoppingVendorResponse responseEntity : list) {
			switch (responseEntity.getStatus()) {
			case "1":
				responseEntity.setStatus("enable");
				break;
			case "0":
				responseEntity.setStatus("disable");
				break;
			}
		}
		LogAction.getInstance().debug("step 3/" + STEP + ":transform status success.");
		*/
		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getVendorid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(category id).");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langcode).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(status).");
		}
		if (!StringUtils.isBlank(entity.getVendorname())) {
			if (entity.getLangcode().equals("all")) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(search vendor name ,lange code can not all).");
			}
		}
	}
}
