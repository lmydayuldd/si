package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingCategoryRequest;
import com.sidc.sits.logical.shopping.ShoppingCategoryProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/category")
public class ShoppingCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -1008706025831030919L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingCategoryServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingCategoryRequest request = (ShoppingCategoryRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingCategoryRequest.class);

		return new ShoppingCategoryProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
