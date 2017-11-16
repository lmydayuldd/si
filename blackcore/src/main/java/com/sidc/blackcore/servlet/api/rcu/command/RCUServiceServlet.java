package com.sidc.blackcore.servlet.api.rcu.command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.rcu.request.ServiceCommandRequest;
import com.sidc.ra.logical.api.rcu.command.RCUServicceProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/rcuservice")
public class RCUServiceServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8688167434866338017L;
	private final static Logger logger = LoggerFactory.getLogger(RCUServiceServlet.class);
	
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		ServiceCommandRequest request = (ServiceCommandRequest) APIParser.getInstance().parse(apiRequest,
				ServiceCommandRequest.class);
		
		return new RCUServicceProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
