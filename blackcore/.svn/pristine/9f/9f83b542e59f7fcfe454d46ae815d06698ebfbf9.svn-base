package com.sidc.blackcore.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeDeviceInsertRequest;
import com.sidc.ra.logical.api.rcumodesetting.RcuModeDeviceDeleteProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/deletemodedevice")
public class RcuModeDeviceDeleteServlet extends APIServlet {
	private static final long serialVersionUID = 8747294329148742072L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeDeviceDeleteServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final RcuModeDeviceInsertRequest enity = (RcuModeDeviceInsertRequest) APIParser.getInstance().parse(apiRequest,
				RcuModeDeviceInsertRequest.class);

		return new RcuModeDeviceDeleteProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}
}
