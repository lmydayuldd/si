package com.sidc.sits.logical.room;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomallocation.bean.RoomListBean;
import com.sidc.blackcore.api.sits.roomallocation.request.HandlerTransferRequest;
import com.sidc.dao.sits.manager.AccountManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class HandlerTransferProcess extends AbstractAuthAPIProcess {
	private final HandlerTransferRequest entity;
	private final String STEP = "1";

	public HandlerTransferProcess(final HandlerTransferRequest entity) {
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
		
		List<String> roomList = new ArrayList<String>();
		
		for (final RoomListBean roomBean : entity.getRoomlist()) {
			roomList.add(roomBean.getRoomno());
		}

		RoomManager.getInstance().transferChatHandler(roomList, entity.getUserid(), entity.getTargetid());
		LogAction.getInstance().debug("step 1/" + STEP + " transfer success.(RoomManager|transferChatHandler)");

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
		if (StringUtils.isBlank(entity.getTargetid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of target id.");
		}
		for (final RoomListBean roomBean : entity.getRoomlist()) {
			if (StringUtils.isBlank(roomBean.getRoomno())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
			}
		}
		if (!AccountManager.getInstance().checkId(entity.getUserid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find user id.");
		}
		if (!AccountManager.getInstance().checkId(entity.getTargetid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find target id.");
		}
	}
}
