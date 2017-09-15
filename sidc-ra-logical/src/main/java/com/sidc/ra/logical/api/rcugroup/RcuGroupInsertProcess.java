/**
 * 
 */
package com.sidc.ra.logical.api.rcugroup;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupInsertRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuGroupInsertProcess extends AbstractAPIProcess {

	private final RcuGroupInsertRequest entity;

	public RcuGroupInsertProcess(final RcuGroupInsertRequest entity) {
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

		RcuGroupManager.getInstance().insertGroup(entity.getGroupname(), entity.getDevices());

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (StringUtils.isBlank(entity.getGroupname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group name)");
		}
		if (entity.getDevices() != null && entity.getDevices().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(devices)");
		}
		for (final int deviceId : entity.getDevices()) {
			if (deviceId <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(device)");
			}
		}
	}

}
