package com.sidc.rcu.hmi.servlet.modedevicesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.modedevicesetting.RcuModeDeviceInsertProcess;
import com.sidc.rcu.hmi.moduledevicesetting.request.RcuModeDeviceInsertRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/insertrcumodedevice")
public class RcuModeDeviceSettingInsertServlet extends APIServlet {
	private static final long serialVersionUID = 4404590278316835786L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeDeviceSettingInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
		final RcuModeDeviceInsertRequest entity = (RcuModeDeviceInsertRequest) APIParser.getInstance().parses(apiRequest,
				RcuModeDeviceInsertRequest.class);

		return new RcuModeDeviceInsertProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
