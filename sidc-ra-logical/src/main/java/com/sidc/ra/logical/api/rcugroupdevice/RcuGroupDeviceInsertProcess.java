package com.sidc.ra.logical.api.rcugroupdevice;

import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceInsertBean;
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuGroupDeviceInsertRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuGroupDeviceInsertProcess extends AbstractAPIProcess {
	private final RcuGroupDeviceInsertRequest entity;

	public RcuGroupDeviceInsertProcess(final RcuGroupDeviceInsertRequest entity) {
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

		RcuGroupManager.getInstance().insertGroupDevice(entity.getGroups());
		LogAction.getInstance().debug("insert success(RcuGroupManager|insertGroupDevice)");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getGroups().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(groups)");
		}
		for (final RcuGroupDeviceInsertBean deviceEntity : entity.getGroups()) {
			if (deviceEntity.getGroupid() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id)");
			}
			if (deviceEntity.getDevices().isEmpty()) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(devices)");
			}
			for (final int deviceId : deviceEntity.getDevices()) {
				if (deviceId <= 0) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(device id)");
				}
			}
		}
	}

}
