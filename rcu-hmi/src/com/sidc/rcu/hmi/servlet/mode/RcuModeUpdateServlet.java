package com.sidc.rcu.hmi.servlet.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.mode.RcuModeUpdateProcess;
import com.sidc.rcu.hmi.modesetting.request.GroupModeUpdateRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mode/update")
public class RcuModeUpdateServlet extends APIServlet {
	private static final long serialVersionUID = -6183936244003867453L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeUpdateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final GroupModeUpdateRequest entity = (GroupModeUpdateRequest) APIParser.getInstance().parses(apiRequest,
				GroupModeUpdateRequest.class);

		return new RcuModeUpdateProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
