package com.sidc.ra.logical.api.rcumodulesetting;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuRoomModuleUpdateRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuGroupManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuRoomModuleUpdateProcess extends AbstractAPIProcess {
	private final RcuRoomModuleUpdateRequest entity;

	public RcuRoomModuleUpdateProcess(final RcuRoomModuleUpdateRequest entity) {
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

		RcuGroupManager.getInstance().updateRoomGroup(entity.getRoomno(), entity.getGroupid());

		LogAction.getInstance().debug("step 1/1: update success(RcuGroupManager|updateRoomGroup).");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal.");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id).");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(room no).");
		}
		if (!RcuGroupManager.getInstance().existRCUGroup(entity.getGroupid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not find group id).");
		}
	}
}
