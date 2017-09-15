package com.sidc.blackcore.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuGroupModeInsertRequest;
import com.sidc.ra.logical.api.rcu.mode.RcuModeInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/mode/insert")
public class RcuModeInsertServlet extends APIServlet {
	private static final long serialVersionUID = -1268198326693510502L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeInsertServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		final RcuGroupModeInsertRequest entity = (RcuGroupModeInsertRequest) APIParser.getInstance().parse(apiRequest,
				RcuGroupModeInsertRequest.class);

		return new RcuModeInsertProcess(entity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
