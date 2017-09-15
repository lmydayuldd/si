/**
 * 
 */
package com.sidc.blackcore.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeSettingInsertRequest;
import com.sidc.ra.logical.api.rcumodesetting.RcuModeSettingInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/insertmodesetting")
public class RcuModeSettingInsertServlet extends APIServlet {
	
	private static final long serialVersionUID = 7592215909142635702L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeSettingInsertServlet.class);

	public RcuModeSettingInsertServlet() {
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RcuModeSettingInsertRequest enity = (RcuModeSettingInsertRequest) APIParser.getInstance().parse(apiRequest,
				RcuModeSettingInsertRequest.class);

		return new RcuModeSettingInsertProcess(enity).execute();
	}
}
