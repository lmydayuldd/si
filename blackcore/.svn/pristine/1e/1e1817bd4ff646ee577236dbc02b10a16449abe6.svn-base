package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingCategoryInsertRequest;
import com.sidc.sits.logical.shopping.ShoppingCategoryInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/categoryinsert")
public class ShoppingCategoryInsertServlet extends APIServlet {
	private static final long serialVersionUID = 2888968640588300032L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingCategoryInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingCategoryInsertRequest request = (ShoppingCategoryInsertRequest) APIParser.getInstance()
				.parse(apiRequest, ShoppingCategoryInsertRequest.class);

		return new ShoppingCategoryInsertProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
