package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingListUpdateRequest;
import com.sidc.sits.logical.shop.ShoppingUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/sits/shoppingupdate")
public class ShoppingUpdateServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5734499895019214092L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingUpdateServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		ShoppingListUpdateRequest request = (ShoppingListUpdateRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingListUpdateRequest.class);
		
		return new ShoppingUpdateProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
