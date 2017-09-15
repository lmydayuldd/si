package com.sidc.rcu.hmi.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.modesetting.RcuModeInsertProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeInsertRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mode/insert")
public class RcuModeInsertServlet extends APIServlet {
	private static final long serialVersionUID = 8134860175623917045L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final ModeInsertRequest entity = (ModeInsertRequest) APIParser.getInstance().parses(apiRequest,
				ModeInsertRequest.class);

		return new RcuModeInsertProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
