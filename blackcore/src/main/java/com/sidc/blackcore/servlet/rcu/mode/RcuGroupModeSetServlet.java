package com.sidc.blackcore.servlet.rcu.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuGroupModeRequest;
import com.sidc.ra.logical.api.rcu.mode.RcuModeDeviceProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/mode/group")
public class RcuGroupModeSetServlet extends APIServlet {
	private static final long serialVersionUID = 5426938351804952859L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupModeSetServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		final RcuGroupModeRequest entity = (RcuGroupModeRequest) APIParser.getInstance().parse(apiRequest,
				RcuGroupModeRequest.class);

		return new RcuModeDeviceProcess(entity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
