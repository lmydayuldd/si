package com.sidc.sits.logical.room;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.message.request.DeviceCheckInStatusRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class DeviceCheckInStatusProcess extends AbstractAPIProcess {
	private final DeviceCheckInStatusRequest entity;

	private final int STEP = 1;

	public DeviceCheckInStatusProcess(final DeviceCheckInStatusRequest entity) {
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

		boolean isCheckIn = RoomManager.getInstance().isCheckInByDevice(entity.getDevices());
		LogAction.getInstance().debug("Step 1/" + STEP + " get data success(RoomManager|isCheckin).");

		return isCheckIn;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getDevices())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(devices).");
		}
	}
}
