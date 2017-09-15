package com.sidc.sits.logical.room;

import java.util.List;

import com.sidc.blackcore.api.sits.room.response.RoomNoListResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.utils.exception.SiDCException;

public class RoomNoListProcess extends AbstractAPIProcess {

	public RoomNoListProcess() {
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		List<String> rooms = RoomManager.getInstance().listRoom();

		return new RoomNoListResponse(rooms);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
	}

}
