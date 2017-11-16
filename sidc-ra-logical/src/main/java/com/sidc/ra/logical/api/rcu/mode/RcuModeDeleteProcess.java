package com.sidc.ra.logical.api.rcu.mode;

import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeDeleteRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeDeleteProcess extends AbstractAPIProcess {
	private final RcuModeDeleteRequest entity;
	private final String step = "1";

	public RcuModeDeleteProcess(final RcuModeDeleteRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		RcuModeManager.getInstance().deleteMode(entity.getModeid());
		LogAction.getInstance().debug("step 1/" + step + ":delete success(RcuModeManager|deleteMode).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getModeid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(mode id)");
		}
	}
}
