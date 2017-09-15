package com.sidc.ra.logical.api.rcumodesetting;

import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeDeviceInsertRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeDeviceManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeDeviceDeleteProcess extends AbstractAPIProcess {
	private final RcuModeDeviceInsertRequest entity;

	public RcuModeDeviceDeleteProcess(final RcuModeDeviceInsertRequest entity) {
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

		RcuModeDeviceManager.getInstance().delete(entity.getModeId(), entity.getList());
		LogAction.getInstance().debug("step 1/1: get device list success(RcuModeDeviceManager|searchDevice).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}

		if (entity.getList().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
	}
}
