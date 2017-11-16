package com.sidc.ra.logical.api.rcu.mode;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeInsertRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuModeInsertProcess extends AbstractAPIProcess {
	private final RcuModeInsertRequest entity;
	private final String step = "1";

	public RcuModeInsertProcess(final RcuModeInsertRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		final int id = RcuModeManager.getInstance().insertMode(entity.getModename(), "", entity.getDevices());
		LogAction.getInstance().debug("step 1/" + step + ":insert success(RcuModeManager|insertMode).");

		return id;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (StringUtils.isBlank(entity.getModename())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(mode name)");
		}
		if (entity.getDevices() == null && entity.getDevices().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(devices)");
		}
		for (final int device : entity.getDevices()) {
			if (device <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(device)");
			}
		}
	}
}
