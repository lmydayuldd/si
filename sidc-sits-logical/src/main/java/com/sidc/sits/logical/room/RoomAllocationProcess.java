package com.sidc.sits.logical.room;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomallocation.bean.RoomListBean;
import com.sidc.blackcore.api.sits.roomallocation.request.RoomAllocationRequest;
import com.sidc.dao.sits.manager.AccountManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RoomAllocationProcess extends AbstractAuthAPIProcess {
	private final RoomAllocationRequest entity;

	public RoomAllocationProcess(final RoomAllocationRequest entity) {
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
		List<RoomListBean> list = entity.getRoomlist();
		List<String> roomList = new ArrayList<String>();

		for (final RoomListBean roomEntity : list) {
			roomList.add(roomEntity.getRoomno());
		}

		RoomManager.getInstance().roomAllocationWithChat(roomList);
		LogAction.getInstance().debug("room allocation success.(RoomManager|roomAllocation)");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of token.");
		}
		if (StringUtils.isBlank(entity.getUserid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of staff id.");
		}
		if (entity.getRoomlist().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room list.");
		}
		for (final RoomListBean roomEntity : entity.getRoomlist()) {
			if (StringUtils.isBlank(roomEntity.getRoomno())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
			}
		}
		if (!AccountManager.getInstance().checkId(entity.getUserid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find user id.");
		}
	}
}
