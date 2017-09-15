package com.sidc.blackcore.servlet.api.rcu.command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.command.request.MobileCommanderRequeset;
import com.sidc.ra.logical.api.rcu.command.RCUHvacCommandProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/rcu/hvaccommand")
public class HvacCommandServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6265981728300616194L;
	private final static Logger logger = LoggerFactory.getLogger(HvacCommandServlet.class);

	public HvacCommandServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		MobileCommanderRequeset request = (MobileCommanderRequeset) APIParser.getInstance().parse(apiRequest,
				MobileCommanderRequeset.class);

		return new RCUHvacCommandProcess(request, req.getLocalAddr()).executeWithMobileToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
