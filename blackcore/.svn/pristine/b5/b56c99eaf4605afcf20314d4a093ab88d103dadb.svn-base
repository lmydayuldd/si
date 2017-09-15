package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCategoryUpdateRequest;
import com.sidc.sits.logical.roomservice.RoomServiceCategoryUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/inroomdining/categoryupdate")
public class RoomServiceCategoryUpdateServlet extends APIServlet {

	private static final long serialVersionUID = -918726063451051283L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceCategoryUpdateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomServiceCategoryUpdateRequest request = (RoomServiceCategoryUpdateRequest) APIParser.getInstance()
				.parse(apiRequest, RoomServiceCategoryUpdateRequest.class);

		return new RoomServiceCategoryUpdateProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
