package com.sidc.sits.logical.room;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.room.request.RoomCheckInInfoRequest;
import com.sidc.blackcore.api.sits.room.response.RoomCheckInInfoResponse;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class CheckInRoomInfoProcess extends AbstractAuthAPIProcess {

	private final RoomCheckInInfoRequest entity;
	private final static int STEP = 1;

	public CheckInRoomInfoProcess(final RoomCheckInInfoRequest entity) {
		super(entity.getToken());
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		final RoomCheckInInfoResponse response = RoomManager.getInstance().listRoomAndGroupOfCheckIn();
		LogAction.getInstance().debug("step 1/" + STEP + " :select success(RoomManager|listRoomAndGroupOfCheckIn).");

		return response;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(token).");
		}
	}

}
