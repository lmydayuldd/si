package com.sidc.blackcore.servlet.api.rcu.command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.rcu.request.HvacCommandRequest;
import com.sidc.blackcore.servlet.api.rcu.mode.RcuModeServlet;
import com.sidc.ra.logical.api.rcu.command.RCUAirConditionCommandProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/rcuhvac")
public class AirConditionCommandServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3722406778007736759L;
	private final static Logger logger = LoggerFactory.getLogger(AirConditionCommandServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		HvacCommandRequest request = (HvacCommandRequest) APIParser.getInstance().parse(apiRequest, HvacCommandRequest.class);
		
		return new RCUAirConditionCommandProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
