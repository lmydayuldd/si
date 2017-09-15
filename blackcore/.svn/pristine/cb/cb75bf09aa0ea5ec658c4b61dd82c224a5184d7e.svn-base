package com.sidc.blackcore.servlet.api.rcu.command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.ra.logical.api.rcu.command.RCUCommandSenderProcess;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/rcu/askcommand")
public class AskCommandServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752628028132237952L;
	private final static Logger logger = LoggerFactory.getLogger(AskCommandServlet.class);

	public AskCommandServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		RCUCommander request = (RCUCommander) APIParser.getInstance().parse(apiRequest, RCUCommander.class);

		return new RCUCommandSenderProcess(request, req.getLocalAddr()).executeWithTesters();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
