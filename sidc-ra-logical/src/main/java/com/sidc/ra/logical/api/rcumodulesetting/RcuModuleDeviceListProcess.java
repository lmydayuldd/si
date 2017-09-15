package com.sidc.ra.logical.api.rcumodulesetting;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodulesetting.request.RcuModuleDeviceListRequest;
import com.sidc.blackcore.api.ra.rcumodulesetting.response.RcuModuleDeviceResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModuleDeviceListProcess extends AbstractAPIProcess {
	private RcuModuleDeviceListRequest entity;

	public RcuModuleDeviceListProcess(final RcuModuleDeviceListRequest entity) {
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
		
		List<RcuModuleDeviceResponse> list = new ArrayList<RcuModuleDeviceResponse>();

//		list = RcuModuleManager.getInstance().searchDeviceById(entity.getGroupId());
		LogAction.getInstance().debug("step 1/1: get device list success(RcuModuleManager|searchDeviceById).");

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
