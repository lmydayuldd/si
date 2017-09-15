package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingVendorRequest;
import com.sidc.sits.logical.shopping.ShoppingVendorProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/vendor")
public class ShoppingVendorServlet extends APIServlet {
	private static final long serialVersionUID = 6879557433264520572L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingVendorServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingVendorRequest request = (ShoppingVendorRequest) APIParser.getInstance().parse(apiRequest,
				ShoppingVendorRequest.class);

		return new ShoppingVendorProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
