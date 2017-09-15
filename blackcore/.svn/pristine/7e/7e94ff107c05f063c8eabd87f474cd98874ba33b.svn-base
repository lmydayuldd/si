package com.sidc.blackcore.servlet.modulesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodulesetting.request.RcuModuleDeviceListRequest;
import com.sidc.ra.logical.api.rcumodulesetting.RcuModuleDeviceListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/listmoduledevice")
public class RcuModuleDeviceListServlet extends APIServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2625087906449169404L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModuleDeviceListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
		final RcuModuleDeviceListRequest enity = (RcuModuleDeviceListRequest) APIParser.getInstance().parse(apiRequest,
				RcuModuleDeviceListRequest.class);

		return new RcuModuleDeviceListProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
