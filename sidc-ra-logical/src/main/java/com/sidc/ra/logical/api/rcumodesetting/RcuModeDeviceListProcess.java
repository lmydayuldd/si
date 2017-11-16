package com.sidc.ra.logical.api.rcumodesetting;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeDeviceListRequest;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeDeviceResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeDeviceManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeDeviceListProcess extends AbstractAPIProcess {
	private final RcuModeDeviceListRequest entity;

	public RcuModeDeviceListProcess(final RcuModeDeviceListRequest entity) {
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

		List<RcuModeDeviceResponse> list = new ArrayList<RcuModeDeviceResponse>();

		list = RcuModeDeviceManager.getInstance().searchDevice(entity.getModeid());
		LogAction.getInstance().debug("step 1/1: get device list success(RcuModeDeviceManager|searchDevice).");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
	}
}
