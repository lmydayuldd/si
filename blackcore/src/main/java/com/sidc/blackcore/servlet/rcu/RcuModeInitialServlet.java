package com.sidc.blackcore.servlet.rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumode.request.ModeInitialRequest;
import com.sidc.ra.logical.api.rcu.mode.RcuModeInitialProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcumodeinitial")
public class RcuModeInitialServlet extends APIServlet {
	private static final long serialVersionUID = -7912704186260764094L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeInitialServlet.class);

	public RcuModeInitialServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		@SuppressWarnings("unchecked")
		final ModeInitialRequest entity = (ModeInitialRequest) APIParser.getInstance().parse(apiRequest,
				ModeInitialRequest.class);

		return new RcuModeInitialProcess(entity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}
}
