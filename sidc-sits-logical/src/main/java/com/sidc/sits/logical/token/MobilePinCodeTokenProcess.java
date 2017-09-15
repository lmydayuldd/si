package com.sidc.sits.logical.token;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.token.request.MobilePinCodeTokenRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.TokenManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class MobilePinCodeTokenProcess extends AbstractAPIProcess {
	private final MobilePinCodeTokenRequest entity;

	public MobilePinCodeTokenProcess(final MobilePinCodeTokenRequest entity) {
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

		final String token = TokenManager.getInstance().insertWithPincode(entity.getPincode(), entity.getInfo(),
				new Timestamp(cal.getTimeInMillis()));
		
		return token;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getPincode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(pin code).");
		}
		if (entity.getInfo() == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(info).");
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
	}
}
