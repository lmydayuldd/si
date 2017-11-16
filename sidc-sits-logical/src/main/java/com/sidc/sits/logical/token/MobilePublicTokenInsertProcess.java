package com.sidc.sits.logical.token;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.token.request.MobilePublicTokenInsertRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.CheckInManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.dao.sits.manager.TokenManager;
import com.sidc.utils.encrypt.AesEncrypt;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class MobilePublicTokenInsertProcess extends AbstractAPIProcess {
	private final MobilePublicTokenInsertRequest entity;

	public MobilePublicTokenInsertProcess(final MobilePublicTokenInsertRequest entity) {
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
		// 暫時
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);

		final String token = TokenManager.getInstance().insertWithPublicKey(entity.getType(), entity.getRoomno(),
				new Timestamp(cal.getTimeInMillis()));

		LogAction.getInstance().debug("step 1/1: insert success(TokenManager|insertWithPublicKey|" + token + ").");

//		AesEncrypt encrypt = new AesEncrypt("sidc");

		// return encrypt.encrypt(token);

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
		if (entity.getType() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(type).");
		}
		if (!CheckInManager.getInstance().findRoom(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find room no.");
		}
		if (!RoomManager.getInstance().isCheckin(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room no is not check in.");
		}
		if (!TokenManager.getInstance().checkExpired(entity.getType(), entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "token not yet expired.");
		}

	}

}
