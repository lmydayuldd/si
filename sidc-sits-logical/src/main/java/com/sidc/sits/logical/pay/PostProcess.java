package com.sidc.sits.logical.pay;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.AgentPostRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.PostManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class PostProcess extends AbstractAPIProcess {

	private AgentPostRequest enity;
	public PostProcess(AgentPostRequest enity) {
		// TODO Auto-generated constructor stub
		this.enity = enity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().setUserId(this.enity.getRoomno());
		PostManager.getInstance().updateStatus(enity);
		
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(enity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (!enity.getStatus().equalsIgnoreCase("POSTED")) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status not \"POSTED\".");
		}
		if (StringUtils.isBlank(enity.getAgentid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of agent id.");
		}
		if (!RoomManager.getInstance().isCheckin(enity.getRoomno())) {
			throw new SiDCException(APIStatus.FAIL_AUTHENTICATION, "Room is not checkin.");
		}
	}
}
