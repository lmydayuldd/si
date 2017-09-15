package com.sidc.rcu.hmi.servlet.modedevicesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.modedevicesetting.ModeDeviceListProcess;
import com.sidc.rcu.hmi.moduledevicesetting.request.RcuModeDeviceRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/listmodedevicesetting")
public class RcuModeDeviceSettingListServlet extends APIServlet {
	private static final long serialVersionUID = 5392039131274526547L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeDeviceSettingListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final RcuModeDeviceRequest entity = (RcuModeDeviceRequest) APIParser.getInstance().parses(apiRequest,
				RcuModeDeviceRequest.class);

		return new ModeDeviceListProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
