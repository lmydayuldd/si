package com.sidc.blackcore.servlet.userauthorization;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.userauthorization.request.AuthorizationListRequest;
import com.sidc.sits.logical.userauthorization.AuthorizationListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/auth/functionlist")
public class AuthorizationListServlet extends APIServlet {
	private static final long serialVersionUID = 7778353680451908769L;
	private final static Logger logger = LoggerFactory.getLogger(AuthorizationListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final AuthorizationListRequest request = (AuthorizationListRequest) APIParser.getInstance().parse(apiRequest,
				AuthorizationListRequest.class);

		return new AuthorizationListProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
