package com.sidc.rcu.hmi.servlet.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.mode.RcuGroupDeviceListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/listmodelsettingdevice")
public class ModeSettingDeviceListServlet extends APIServlet {
	private static final long serialVersionUID = -8529457795143456572L;
	private final static Logger logger = LoggerFactory.getLogger(ModeSettingDeviceListServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return new RcuGroupDeviceListProcess().execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
