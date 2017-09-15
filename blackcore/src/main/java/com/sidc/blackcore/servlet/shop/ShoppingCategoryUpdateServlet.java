package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingCategoryUpdateRequest;
import com.sidc.sits.logical.shopping.ShoppingCategoryUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/categoryupdate")
public class ShoppingCategoryUpdateServlet extends APIServlet {
	private static final long serialVersionUID = -8395995148715151971L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingCategoryUpdateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingCategoryUpdateRequest request = (ShoppingCategoryUpdateRequest) APIParser.getInstance()
				.parse(apiRequest, ShoppingCategoryUpdateRequest.class);

		return new ShoppingCategoryUpdateProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
