package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCategoryInsertRequest;
import com.sidc.sits.logical.roomservice.RoomServiceCategoryInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/inroomdining/categoryinsert")
public class RoomServiceCategoryInsertServlet extends APIServlet {

	private static final long serialVersionUID = -6648484223222516846L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceCategoryInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceCategoryInsertRequest request = (RoomServiceCategoryInsertRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceCategoryInsertRequest.class);

		return new RoomServiceCategoryInsertProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
