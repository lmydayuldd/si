package com.sidc.rcu.hmi.servlet.rcucommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.rcucommand.HvacCenterControlProcess;
import com.sidc.rcu.hmi.rcucommand.request.HvacCenterControlRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/hvacentercontrol")
public class HvacCenterControlServlet extends APIServlet {

	private static final long serialVersionUID = 208446702468335515L;
	private final static Logger logger = LoggerFactory.getLogger(HvacCenterControlServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final HvacCenterControlRequest entity = (HvacCenterControlRequest) APIParser.getInstance().parses(apiRequest,
				HvacCenterControlRequest.class);

		return new HvacCenterControlProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
