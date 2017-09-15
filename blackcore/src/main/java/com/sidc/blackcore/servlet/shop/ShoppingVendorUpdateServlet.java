package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingVendorUpdateRequest;
import com.sidc.sits.logical.shopping.ShoppingVendorUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/vendorupdate")
public class ShoppingVendorUpdateServlet extends APIServlet {
	private static final long serialVersionUID = 3341663183142205782L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingVendorUpdateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingVendorUpdateRequest request = (ShoppingVendorUpdateRequest) APIParser.getInstance()
				.parse(apiRequest, ShoppingVendorUpdateRequest.class);

		return new ShoppingVendorUpdateProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
