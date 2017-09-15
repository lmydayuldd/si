package com.sidc.blackcore.servlet.message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.agent.request.MessageRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.message.MessageUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/messagemodify")
public class MessageUpdateServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5786971210238053543L;
	private final static Logger logger = LoggerFactory.getLogger(MessageUpdateServlet.class);

	public MessageUpdateServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		MessageRequest enity = (MessageRequest) APIParser.getInstance().parse(apiRequest, MessageRequest.class);

		return new MessageUpdateProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
