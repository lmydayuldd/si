package com.sidc.ra.logical.api.rcu.sos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.ra.sosrecord.request.SosRecordRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RoomManager;
import com.sidc.dao.ra.manager.SosRecordManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class SosRecordProcess extends AbstractAPIProcess {
	private final SosRecordRequest entity;
	private final String step = "1";

	public SosRecordProcess(SosRecordRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		SosRecordManager.getInstance().insert(entity.getRoomno(), entity.getAction(), entity.getClientip(),
				entity.getClientrole());
		LogAction.getInstance().debug("step 1/" + step + ":insert success(SosRecordManager|insert).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(roomno)");
		}
		if (StringUtils.isBlank(entity.getAction())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(action)");
		}
		if (StringUtils.isBlank(entity.getClientip())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(client ip)");
		}
		if (StringUtils.isBlank(entity.getClientrole())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(clien role)");
		}

		final String[] actions = { "occur", "stopalarm", "tolift" };
		final String[] roles = { "hmiuser", "system" };

		if (!ArrayUtils.contains(actions, entity.getAction())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not find actions)");
		}

		if (!ArrayUtils.contains(roles, entity.getClientrole())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not find client role)");
		}

		if (!isIp(entity.getClientip())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(client ip format)");
		}
		if (!RoomManager.getInstance().isExist(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not find room no)");
		}
	}

	/**
	 * check ip format
	 * 
	 * @param ip
	 * @return
	 */
	private boolean isIp(final String ip) {
		if (ip.length() < 7 || ip.length() > 15) {
			return false;
		}

		final String regularExpression = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

		final Pattern pattern = Pattern.compile(regularExpression);

		final Matcher matcher = pattern.matcher(ip);

		return matcher.find();
	}
}
