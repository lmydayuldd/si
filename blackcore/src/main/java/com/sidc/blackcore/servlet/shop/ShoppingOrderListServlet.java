package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingOrderListRequest;
import com.sidc.sits.logical.shopping.ShoppingOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/orderlist")
public class ShoppingOrderListServlet extends APIServlet {
	private static final long serialVersionUID = -662658578732670418L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingOrderListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingOrderListRequest request = (ShoppingOrderListRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingOrderListRequest.class);

		return new ShoppingOrderListProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
