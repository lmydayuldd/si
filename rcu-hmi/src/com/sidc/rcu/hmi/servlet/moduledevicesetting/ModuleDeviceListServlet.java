package com.sidc.rcu.hmi.servlet.moduledevicesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.moduledevicesetting.ModuleDeviceListProcess;
import com.sidc.rcu.hmi.moduledevicesetting.request.ModuleDeviceRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/listmoduledevice")
public class ModuleDeviceListServlet extends APIServlet {

	private static final long serialVersionUID = 5268237342621285008L;
	private final static Logger logger = LoggerFactory.getLogger(ModuleDeviceListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

//		final ModuleDeviceRequest entity = (ModuleDeviceRequest) APIParser.getInstance().parses(apiRequest,
//				ModuleDeviceRequest.class);
		

		return new ModuleDeviceListProcess(new ModuleDeviceRequest(1)).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
