package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingOrderStatusRequest;
import com.sidc.sits.logical.shopping.ShoppingOrderStatusProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/orderstatus")
public class ShoppingOrderStatusServlet extends APIServlet {
	private static final long serialVersionUID = -8915035095824613006L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingOrderStatusServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingOrderStatusRequest request = (ShoppingOrderStatusRequest) APIParser.getInstance()
				.parse(apiRequest, ShoppingOrderStatusRequest.class);

		return new ShoppingOrderStatusProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
