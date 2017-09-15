package com.sidc.sits.logical.token;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.token.request.MobilePrivateTokenInsertRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.CheckInManager;
import com.sidc.dao.sits.manager.TokenManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class MobilePrivateTokenInsertProcess extends AbstractAPIProcess {
	private final MobilePrivateTokenInsertRequest entity;
	private String publicKey = null;

	public MobilePrivateTokenInsertProcess(final MobilePrivateTokenInsertRequest entity) {
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

		final String token = TokenManager.getInstance().insertWithPrivateKey(this.publicKey, entity.getRoomno(),
				entity.getIp(), entity.getInfo());

		LogAction.getInstance().debug("step 1/1: insert success(TokenManager|insertWithPrivateKey|" + token + ").");

//		AesEncrypt encrypt = new AesEncrypt("sidc");

//		return encrypt.encrypt(token);
		
		return token;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getInfo() == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(mobile info).");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(publickey).");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(room no).");
		}
		if (StringUtils.isBlank(entity.getInfo().getDeviceid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(device id).");
		}
		if (StringUtils.isBlank(entity.getInfo().getPushtoken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(push token).");
		}
		if (StringUtils.isBlank(entity.getInfo().getVersion())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(version).");
		}
		if (entity.getInfo().getOperatingsystem() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(operating system).");
		}
		if (StringUtils.isBlank(entity.getInfo().getDeviceid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(pin code).");
		}
		if (entity.getType() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(type).");
		}
		if (StringUtils.isBlank(entity.getIp())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(stb ip).");
		}
		if (!CheckInManager.getInstance().findRoom(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find room no.");
		}
		if (!StringUtils.isBlank(CheckInManager.getInstance().findRoomCheckOutStatus(entity.getRoomno()))) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room is not check in.");
		}
//		if (!StbListManager.getInstance().isExisted(entity.getRoomno(), entity.getIp())) {
//			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find stb.");
//		}

//		AesEncrypt encrypt = new AesEncrypt("sidc");

//		this.publicKey = encrypt.decrypt(entity.getPublickey());
		
		publicKey = entity.getPublickey();

		if (!TokenManager.getInstance().checkExpired(publicKey, entity.getType(), entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "token failure.");
		}
//		if (TokenManager.getInstance().checkMobileInfo(publicKey, entity.getRoomno(), entity.getIp(),
//				entity.getInfo().getDeviceid(), entity.getInfo().getOperatingsystem(), entity.getInfo().getVersion())) {
//			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "the mobile info is same.");
//		}
	}

}
