package com.sidc.blackcore.servlet.roomservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingListRequest;
import com.sidc.sits.logical.roomservice.RoomServiceProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/sits/roomservice")
public class RoomServiceServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9215831667503783973L;
	private final static Logger logger = LoggerFactory.getLogger(RoomServiceServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		ShoppingListRequest request = (ShoppingListRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingListRequest.class);
		
		return new RoomServiceProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
