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
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuGroupDeviceListRequest;
import com.sidc.ra.logical.api.rcugroupdevice.RcuGroupDeviceListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/device")
public class RcuGroupDeviceServlet extends APIServlet {
	private static final long serialVersionUID = -7708491268634645951L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupDeviceServlet.class);

	public RcuGroupDeviceServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RcuGroupDeviceListRequest entity = (RcuGroupDeviceListRequest) APIParser.getInstance().parse(apiRequest,
				RcuGroupDeviceListRequest.class);

		return new RcuGroupDeviceListProcess(entity).execute();
	}

}
