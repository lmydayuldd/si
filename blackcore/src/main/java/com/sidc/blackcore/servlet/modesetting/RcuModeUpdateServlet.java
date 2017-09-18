package com.sidc.blackcore.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuGroupModeUpdateRequest;
import com.sidc.ra.logical.api.rcu.mode.RcuModeUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/mode/update")
public class RcuModeUpdateServlet extends APIServlet {
	private static final long serialVersionUID = 3838789360321086911L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeUpdateServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		final RcuGroupModeUpdateRequest entity = (RcuGroupModeUpdateRequest) APIParser.getInstance().parse(apiRequest,
				RcuGroupModeUpdateRequest.class);

		return new RcuModeUpdateProcess(entity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
