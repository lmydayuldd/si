package com.sidc.blackcore.servlet.rcu.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeDeleteRequest;
import com.sidc.ra.logical.api.rcu.mode.RcuModeDeleteProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/mode/delete")
public class RcuModeDeleteServlet extends APIServlet {
	private static final long serialVersionUID = -1378649283961446935L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeDeleteServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		final RcuModeDeleteRequest entity = (RcuModeDeleteRequest) APIParser.getInstance().parse(apiRequest,
				RcuModeDeleteRequest.class);

		return new RcuModeDeleteProcess(entity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
