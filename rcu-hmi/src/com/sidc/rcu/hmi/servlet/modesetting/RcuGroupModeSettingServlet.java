package com.sidc.rcu.hmi.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.modesetting.RcuGroupModeProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingListRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/group/mode")
public class RcuGroupModeSettingServlet extends APIServlet {
	private static final long serialVersionUID = 1544764573967086799L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupModeSettingServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final ModeSettingListRequest entity = (ModeSettingListRequest) APIParser.getInstance().parses(apiRequest,
				ModeSettingListRequest.class);

		return new RcuGroupModeProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
