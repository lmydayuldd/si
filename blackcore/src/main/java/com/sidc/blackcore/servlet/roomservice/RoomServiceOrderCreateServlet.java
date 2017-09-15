package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCreateOrderRequest;
import com.sidc.sits.logical.roomservice.RoomServiceOrderCreateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/inroomdining/order")
public class RoomServiceOrderCreateServlet extends APIServlet {

	private static final long serialVersionUID = -3659930458081783225L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceOrderCreateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceCreateOrderRequest request = (RoomServiceCreateOrderRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceCreateOrderRequest.class);

		return new RoomServiceOrderCreateProcess(request).executeWithMobileToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
