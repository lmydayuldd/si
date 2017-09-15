package com.sidc.blackcore.servlet.api.rcu.command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcuhvaccentercontrol.request.HvacCenterControlRequest;
import com.sidc.ra.logical.api.rcuhvaccentercontrol.HvacCenterControlProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/hvaccentercontrol")
public class HvacCenterContorlServlet extends APIServlet {
	private static final long serialVersionUID = 1353117745056047514L;
	private final static Logger logger = LoggerFactory.getLogger(HvacCenterContorlServlet.class);

	public HvacCenterContorlServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final HvacCenterControlRequest request = (HvacCenterControlRequest) APIParser.getInstance().parse(apiRequest,
				HvacCenterControlRequest.class);

		return new HvacCenterControlProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
