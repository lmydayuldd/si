package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceOrderStatusRequest;
import com.sidc.sits.logical.roomservice.RoomServiceOrderStatusProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/inroomdining/orderstatus")
public class RoomServiceOrderStatusServlet extends APIServlet {

	private static final long serialVersionUID = -2130367823843372399L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceOrderStatusServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceOrderStatusRequest request = (RoomServiceOrderStatusRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceOrderStatusRequest.class);

		return new RoomServiceOrderStatusProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
