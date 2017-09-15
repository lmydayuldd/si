package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceBackendOrderListRequest;
import com.sidc.sits.logical.roomservice.RoomServiceBackendOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/backend/inroomdining/orderlist")
public class RoomServiceBackendOrderListServlet extends APIServlet {

	private static final long serialVersionUID = -3469438989405316102L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceBackendOrderListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceBackendOrderListRequest request = (RoomServiceBackendOrderListRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceBackendOrderListRequest.class);

		return new RoomServiceBackendOrderListProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
