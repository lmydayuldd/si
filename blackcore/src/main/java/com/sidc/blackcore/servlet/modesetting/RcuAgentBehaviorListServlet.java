/**
 * 
 */
package com.sidc.blackcore.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.ra.logical.api.rcumodesetting.RcuAgentBehaviorListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/agentbehaviorlist")
public class RcuAgentBehaviorListServlet extends APIServlet {

	private static final long serialVersionUID = 3275655692811732925L;
	private final static Logger logger = LoggerFactory.getLogger(RcuAgentBehaviorListServlet.class);

	public RcuAgentBehaviorListServlet() {
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		return new RcuAgentBehaviorListProcess().execute();
	}
}
