package com.sidc.rcu.engine.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.engine.abs.APIParser;
import com.sidc.rcu.engine.abs.APIServlet;
import com.sidc.rcu.engine.behavior.RCUCommandProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/rcucommand")
public class RCUCommandServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6421310977619109085L;
	private final static Logger logger = LoggerFactory.getLogger(RCUCommandServlet.class);

	public RCUCommandServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		RCUCommander rcuCommander = (RCUCommander) APIParser.getInstance().parse(apiRequest, RCUCommander.class);
		
		return new RCUCommandProcess(rcuCommander).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
	
}
