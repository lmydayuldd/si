package com.sidc.sits.logical.shopping;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingVendorInsertRequest;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ShoppingVendorInsertProcess extends AbstractAuthAPIProcess {

	private final ShoppingVendorInsertRequest entity;
	private final String STEP = "1";

	public ShoppingVendorInsertProcess(final ShoppingVendorInsertRequest entity) {
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
		final int id = ShopManager.getInstance().insertVendor(entity.getStatus(), entity.getTex(), entity.getEmail(),
				entity.getAddress(), entity.getList());
		LogAction.getInstance().debug("step 1/" + STEP + ":insert success(ShopManager|insertVendor).");

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
		if (entity.getStatus() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(status).");
		}
		if (!StringUtils.isBlank(entity.getTex()) && entity.getTex().length() > 30) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(tex length).");
		}
		if (!StringUtils.isBlank(entity.getAddress()) && entity.getTex().length() > 150) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(address length).");
		}
		if (!StringUtils.isBlank(entity.getEmail())) {
			if (entity.getEmail().length() > 150) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(email length).");
			}
			if (!entity.getEmail().matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(email format).");
			}
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
			if (langEntity.getName().length() > 50) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(name length).");
			}
			if (langEntity.getLangcode().length() > 5) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(lang code length).");
			}
			if (!StringUtils.isBlank(langEntity.getDescription()) && langEntity.getDescription().length() > 2048) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(description length).");
			}
		}
	}
}
