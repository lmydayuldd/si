package com.sidc.blackcore.servlet.rcu.mode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.ra.logical.api.rcu.mode.RCUModeProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/rcumode")
public class RcuModeServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -662323878809297165L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeServlet.class);
	public RcuModeServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		RcuRoomMode request = (RcuRoomMode) APIParser.getInstance().parse(apiRequest, RcuRoomMode.class);
		
		return new RCUModeProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
