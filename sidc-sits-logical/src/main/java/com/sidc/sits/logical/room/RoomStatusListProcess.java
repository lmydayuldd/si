package com.sidc.sits.logical.room;

import com.sidc.blackcore.api.sits.room.response.RoomStatsListResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.utils.exception.SiDCException;

public class RoomStatusListProcess extends AbstractAPIProcess {

	public RoomStatusListProcess() {
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		final RoomStatsListResponse result = RoomManager.getInstance().listCheckInCheckOutRoom();

		return result;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
	}

}
