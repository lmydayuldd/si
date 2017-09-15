package com.sidc.sits.logical.account;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.account.request.SystemUserLogoutRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.AccountManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class SystemUserLogoutProcess extends AbstractAPIProcess {
	private final SystemUserLogoutRequest entity;
	private final String STEP = "1";

	public SystemUserLogoutProcess(final SystemUserLogoutRequest entity) {
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

		AccountManager.getInstance().logout(entity.getUser());
		LogAction.getInstance().debug("step 1/" + STEP + ": logout success(AccountManager|logout).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getUser())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(user).");
		}

	}
}
