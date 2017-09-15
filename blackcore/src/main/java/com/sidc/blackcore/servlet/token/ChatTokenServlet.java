package com.sidc.blackcore.servlet.token;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.token.request.ChatTokenRequest;
import com.sidc.sits.logical.token.ChatTokenInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mobile/token/chat")
public class ChatTokenServlet extends APIServlet {

	private static final long serialVersionUID = -4697644144958671074L;
	private final static Logger logger = LoggerFactory.getLogger(ChatTokenServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final ChatTokenRequest entity = (ChatTokenRequest) APIParser.getInstance().parse(apiRequest,
				ChatTokenRequest.class);

		return new ChatTokenInsertProcess(entity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
