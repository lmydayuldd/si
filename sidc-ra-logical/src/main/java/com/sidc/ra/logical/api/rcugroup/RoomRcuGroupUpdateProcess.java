package com.sidc.ra.logical.api.rcugroup;

import com.sidc.blackcore.api.ra.rcugroup.request.RoomRcuGroupUpdateRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RoomRCUManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RoomRcuGroupUpdateProcess extends AbstractAPIProcess {
	private final String step = "1";
	private final RoomRcuGroupUpdateRequest entity;

	public RoomRcuGroupUpdateProcess(final RoomRcuGroupUpdateRequest entity) {
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

		RoomRCUManager.getInstance().update(entity.getGroupid(), entity.getRooms());
		LogAction.getInstance().debug("Step 1/" + step + ":update success(RoomRCUManager|update).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal.");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id).");
		}
		if (entity.getRooms() == null || entity.getRooms().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(rooms).");
		}
	}
}
