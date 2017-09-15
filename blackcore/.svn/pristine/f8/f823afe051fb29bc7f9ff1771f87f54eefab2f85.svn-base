package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingListRequest;
import com.sidc.sits.logical.shop.ShoppingProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/sits/shopping")
public class ShoppingServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3520719519904969667L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		ShoppingListRequest request = (ShoppingListRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingListRequest.class);

		return new ShoppingProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
