package com.sidcsits.logical.rcu.mode;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.sdk.blackcore.rcu.mode.RcuModeClient;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
public class RoomModeProcess extends AbstractAPIProcess {

	private RcuRoomMode rcuRoomMode;
	public RoomModeProcess(RcuRoomMode rcuRoomMode) {
		// TODO Auto-generated constructor stub
		this.rcuRoomMode = rcuRoomMode;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + this.rcuRoomMode);
		LogAction.getInstance().setUserId(this.rcuRoomMode.getRoomno());
		LogAction.getInstance().setContent(this.rcuRoomMode.getMode());
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return new RcuModeClient<Object>(UrlUtils.getUrl(SidcUrlName.BLACKCORE.toString()), rcuRoomMode).execute();
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}
}
