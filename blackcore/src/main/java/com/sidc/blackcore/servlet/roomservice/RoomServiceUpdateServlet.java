package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingListUpdateRequest;
import com.sidc.sits.logical.roomservice.RoomServiceUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/sits/roomserviceupdate")
public class RoomServiceUpdateServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3683330140940713066L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceUpdateServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		ShoppingListUpdateRequest request = (ShoppingListUpdateRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingListUpdateRequest.class);
		
		return new RoomServiceUpdateProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
