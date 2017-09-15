package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCategoryRequest;
import com.sidc.sits.logical.roomservice.RoomServiceCategoryProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/inroomdining/category")
public class RoomServiceCategoryServlet extends APIServlet {

	private static final long serialVersionUID = -8447736220226621596L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceCategoryServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceCategoryRequest request = (RoomServiceCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceCategoryRequest.class);

		return new RoomServiceCategoryProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
