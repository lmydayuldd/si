package com.sidc.blackcore.servlet.message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.message.request.GuestMessageRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.message.GuestMessageProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mobile/guestmessage")
public class GuestMessageServlet extends APIServlet {

	private static final long serialVersionUID = 6121539266412003392L;
	private final static Logger logger = LoggerFactory.getLogger(GuestMessageServlet.class);

	public GuestMessageServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final GuestMessageRequest enity = (GuestMessageRequest) APIParser.getInstance().parse(apiRequest,
				GuestMessageRequest.class);

		return new GuestMessageProcess(enity).executeWithMobileToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
