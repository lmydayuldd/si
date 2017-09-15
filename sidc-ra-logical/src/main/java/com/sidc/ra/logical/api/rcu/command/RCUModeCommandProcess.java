package com.sidc.ra.logical.api.rcu.command;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.ra.command.request.MobileCommanderRequeset;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.ra.logical.abs.AbstractAuthAPIProcess;
import com.sidc.ra.logical.api.rcu.mode.RCUModeProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RCUModeCommandProcess extends AbstractAuthAPIProcess {
	private MobileCommanderRequeset entity;

	public RCUModeCommandProcess(final MobileCommanderRequeset entity, final String ip) {
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno(), ip);
		this.entity = entity;
	}

	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + this.entity);
	}

	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		RcuRoomMode modeEntity = new RcuRoomMode(entity.getRoomno(), entity.getModename());

		return new RCUModeProcess(modeEntity).execute();
	}

	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Rquest is illegal.");
		}
		if (StringUtils.isBlank(this.entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "RoomNo is empty.");
		}
		if (StringUtils.isBlank(this.entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Public key is empty.");
		}
		if (StringUtils.isBlank(this.entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Private key is empty.");
		}
		if (StringUtils.isBlank(this.entity.getModename())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Mode name is empty.");
		}
	}

}
