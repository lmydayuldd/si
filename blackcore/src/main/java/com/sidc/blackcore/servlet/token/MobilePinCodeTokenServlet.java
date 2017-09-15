package com.sidc.blackcore.servlet.token;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.token.request.MobilePinCodeTokenRequest;
import com.sidc.sits.logical.token.MobilePinCodeTokenProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mobile/pincodetoken")
public class MobilePinCodeTokenServlet extends APIServlet {
	private static final long serialVersionUID = -7184606339322561933L;
	private final static Logger logger = LoggerFactory.getLogger(MobilePinCodeTokenServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final MobilePinCodeTokenRequest enity = (MobilePinCodeTokenRequest) APIParser.getInstance().parse(apiRequest,
				MobilePinCodeTokenRequest.class);

		return new MobilePinCodeTokenProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
