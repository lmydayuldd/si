package com.sidc.sits.logical.account;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.account.request.SystemUserInsertRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.AccountManager;
import com.sidc.utils.encrypt.AesEncrypt;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class SystemUserInsertProcess extends AbstractAPIProcess {
	private final SystemUserInsertRequest entity;
	private final String STEP = "1";

	public SystemUserInsertProcess(final SystemUserInsertRequest entity) {
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

		AesEncrypt encrpyt = new AesEncrypt("sidc");

		AccountManager.getInstance().insert(entity.getId(), entity.getName(), entity.getEmail(),
				encrpyt.encrypt(entity.getPassword()), entity.getStaffcode());
		LogAction.getInstance().debug("step 1/" + STEP + ": insert success(AccountManager|insert).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getId())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(id).");
		}
		if (StringUtils.isBlank(entity.getName())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(name).");
		}
		if (StringUtils.isBlank(entity.getEmail())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(email).");
		}
		if (StringUtils.isBlank(entity.getPassword())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(password).");
		}
		if (StringUtils.isBlank(entity.getStaffcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(staff code).");
		}
		if (entity.getId().length() > 40) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(id length).");
		}
		if (entity.getName().length() > 30) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(name length).");
		}
		if (entity.getEmail().length() > 40) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(email length).");
		}
		if (entity.getPassword().length() > 30) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(password length).");
		}
		if (entity.getStaffcode().length() > 12) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(staff code length).");
		}
		if (AccountManager.getInstance().checkId(entity.getId())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "this id is not allowed to use.");
		}

	}
}
