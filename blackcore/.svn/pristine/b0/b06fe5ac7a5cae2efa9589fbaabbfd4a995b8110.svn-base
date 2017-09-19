package com.sidc.blackcore.servlet.rcu.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeDeviceInsertRequest;
import com.sidc.ra.logical.api.rcumodesetting.RcuModeDeviceInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/insertmodedevice")
public class RcuModeDeviceInsertServlet extends APIServlet {
	private static final long serialVersionUID = 8747294329148742072L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeDeviceInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final RcuModeDeviceInsertRequest enity = (RcuModeDeviceInsertRequest) APIParser.getInstance().parse(apiRequest,
				RcuModeDeviceInsertRequest.class);

		return new RcuModeDeviceInsertProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}
}
