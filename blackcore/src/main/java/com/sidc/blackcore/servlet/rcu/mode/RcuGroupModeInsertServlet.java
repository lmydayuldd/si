package com.sidc.blackcore.servlet.rcu.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuGroupModeInsertRequest;
import com.sidc.ra.logical.api.rcu.mode.RcuGroupModeInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/mode/insert")
public class RcuGroupModeInsertServlet extends APIServlet {
	private static final long serialVersionUID = -1268198326693510502L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupModeInsertServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		final RcuGroupModeInsertRequest entity = (RcuGroupModeInsertRequest) APIParser.getInstance().parse(apiRequest,
				RcuGroupModeInsertRequest.class);

		return new RcuGroupModeInsertProcess(entity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
