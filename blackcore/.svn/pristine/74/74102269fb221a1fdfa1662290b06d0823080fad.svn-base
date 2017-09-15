package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceItemInsertRequest;
import com.sidc.sits.logical.roomservice.RoomServiceItemInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/inroomdining/iteminsert")
public class RoomServiceItemInsertServlet extends APIServlet {

	private static final long serialVersionUID = -6648484223222516846L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceItemInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceItemInsertRequest request = (RoomServiceItemInsertRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceItemInsertRequest.class);

		return new RoomServiceItemInsertProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
