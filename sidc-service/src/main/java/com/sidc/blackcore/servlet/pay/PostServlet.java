package com.sidc.blackcore.servlet.pay;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.agent.request.AgentPostRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.pay.PostProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/post")
public class PostServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2850470911262057376L;
	private final static Logger logger = LoggerFactory.getLogger(PostServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		AgentPostRequest enity = (AgentPostRequest) APIParser.getInstance().parse(apiRequest, AgentPostRequest.class);
		
		return new PostProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
