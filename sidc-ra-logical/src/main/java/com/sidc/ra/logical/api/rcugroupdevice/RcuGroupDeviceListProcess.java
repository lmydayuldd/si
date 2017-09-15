package com.sidc.ra.logical.api.rcugroupdevice;

import java.util.List;

import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceBean;
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuGroupDeviceListRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuGroupDeviceListProcess extends AbstractAPIProcess {
	private final RcuGroupDeviceListRequest entity;

	public RcuGroupDeviceListProcess(final RcuGroupDeviceListRequest entity) {
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
		final List<RcuGroupDeviceBean> list = RcuGroupManager.getInstance().findGroupDevice(entity.getGroupid());

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getGroupid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id)");
		}
	}
}
