package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceOrderListRequest;
import com.sidc.sits.logical.roomservice.RoomServiceOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/inroomdining/orderlist")
public class RoomServiceOrderListServlet extends APIServlet {

	private static final long serialVersionUID = -2748805380958230797L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceOrderListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceOrderListRequest request = (RoomServiceOrderListRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceOrderListRequest.class);

		return new RoomServiceOrderListProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
