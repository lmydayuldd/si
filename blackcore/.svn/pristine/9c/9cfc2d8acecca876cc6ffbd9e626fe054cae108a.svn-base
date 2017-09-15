package com.sidc.blackcore.servlet.rcugroupdevice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuDeviceListRequest;
import com.sidc.ra.logical.api.rcugroupdevice.RcuGroupDeviceProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/listdevicebygroup")
public class RcuGroupDeviceListServlet extends APIServlet {

	private static final long serialVersionUID = 8754880041118166688L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupDeviceListServlet.class);

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RcuDeviceListRequest enity = (RcuDeviceListRequest) APIParser.getInstance().parse(apiRequest,
				RcuDeviceListRequest.class);

		return new RcuGroupDeviceProcess(enity).execute();
	}
}
