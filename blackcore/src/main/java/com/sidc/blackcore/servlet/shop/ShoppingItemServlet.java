package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingItemRequest;
import com.sidc.sits.logical.shopping.ShoppingItemProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/item")
public class ShoppingItemServlet extends APIServlet {
	private static final long serialVersionUID = 6879557433264520572L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingItemServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingItemRequest request = (ShoppingItemRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingItemRequest.class);

		return new ShoppingItemProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
