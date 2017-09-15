package com.sidc.sits.logical.account;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.account.bean.SaffInfoBean;
import com.sidc.blackcore.api.sits.account.request.StaffInfoRequest;
import com.sidc.dao.sits.manager.StaffManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class StaffInfoProcess extends AbstractAuthAPIProcess {
	private final StaffInfoRequest entity;

	public StaffInfoProcess(final StaffInfoRequest entity) {
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
		// TODO Auto-generated method stub

		final SaffInfoBean saffInfoBean = StaffManager.getInstance().getStaffInfoWithTokenInfo(entity.getToken(),
				entity.getStaffid());

		LogAction.getInstance().debug("step 1/2: get bean success(StaffManager|getStaffInfoWithTokenInfo).");

		return saffInfoBean;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(token).");
		}
		if (StringUtils.isBlank(entity.getStaffid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(staff id).");
		}
		if (!StaffManager.getInstance().isExist(entity.getToken(), entity.getStaffid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "token and staff id is not match.");
		}
	}
}
