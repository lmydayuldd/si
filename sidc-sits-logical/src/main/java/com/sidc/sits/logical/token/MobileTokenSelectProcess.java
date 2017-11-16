package com.sidc.sits.logical.token;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.token.request.MobilePublicTokenInsertRequest;
import com.sidc.blackcore.api.sits.token.request.TokenSelectRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.CheckInManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.dao.sits.manager.TokenManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class MobileTokenSelectProcess extends AbstractAPIProcess {
	private TokenSelectRequest entity;

	public MobileTokenSelectProcess(final TokenSelectRequest entity) {
		// TODO Auto-generated constructor stub
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

		String token = TokenManager.getInstance().select(entity.getRoomno());
		LogAction.getInstance().debug("step 1/1: insert success(TokenManager|select|" + token + ").");

		if (StringUtils.isBlank(token)) {
			LogAction.getInstance().debug("token is empty.");
			token = createPublicToken(entity.getRoomno());
		}

		return token;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(room no).");
		}
		if (!CheckInManager.getInstance().findRoom(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find room no.");
		}
		if (!RoomManager.getInstance().isCheckin(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room no is not check in.");
		}
	}

	private String createPublicToken(final String roomNo) throws SiDCException, Exception {
		MobilePublicTokenInsertRequest request = new MobilePublicTokenInsertRequest(roomNo, 0);
		return (String) new MobilePublicTokenInsertProcess(request).execute();
	}

}
