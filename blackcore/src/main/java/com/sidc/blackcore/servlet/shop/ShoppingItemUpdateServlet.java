package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingItemUpdateRequest;
import com.sidc.sits.logical.shopping.ShoppingItemUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/itemupdate")
public class ShoppingItemUpdateServlet extends APIServlet {
	private static final long serialVersionUID = 3366207001950727733L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingItemUpdateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingItemUpdateRequest request = (ShoppingItemUpdateRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingItemUpdateRequest.class);

		return new ShoppingItemUpdateProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
