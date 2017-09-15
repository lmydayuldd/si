/**
 * 
 */
package com.sidc.blackcore.servlet.rcugroupdevice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuGroupDeviceInsertRequest;
import com.sidc.ra.logical.api.rcugroupdevice.RcuGroupDeviceInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/device/insert")
public class RcuGroupDeviceInsertServlet extends APIServlet {
	private static final long serialVersionUID = -3408933251461024112L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupDeviceInsertServlet.class);

	public RcuGroupDeviceInsertServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RcuGroupDeviceInsertRequest entity = (RcuGroupDeviceInsertRequest) APIParser.getInstance()
				.parse(apiRequest, RcuGroupDeviceInsertRequest.class);

		return new RcuGroupDeviceInsertProcess(entity).execute();
	}

}
