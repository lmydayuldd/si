package com.sidc.rcu.hmi.servlet.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.mode.RcuModeDeleteProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeDeleteRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/mode/delete")
public class ModeDeleteServlet extends APIServlet {
	private static final long serialVersionUID = -778641178496699908L;
	private final static Logger logger = LoggerFactory.getLogger(ModeDeleteServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final ModeDeleteRequest entity = (ModeDeleteRequest) APIParser.getInstance().parses(apiRequest,
				ModeDeleteRequest.class);

		return new RcuModeDeleteProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
