package com.sidc.blackcore.servlet.message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.agent.request.MessageRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.message.MessageInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/messagebuild")
public class MessageInsertServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2414933109531429689L;
	private final static Logger logger = LoggerFactory.getLogger(MessageInsertServlet.class);
	
	public MessageInsertServlet() {
		// TODO Auto-generated constructor stub
	}

	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		MessageRequest enity = (MessageRequest) APIParser.getInstance().parse(apiRequest, MessageRequest.class);
		
		return new MessageInsertProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
