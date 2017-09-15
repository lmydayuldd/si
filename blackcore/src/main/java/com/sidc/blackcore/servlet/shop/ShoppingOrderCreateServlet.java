package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShoppingOrderCreateRequest;
import com.sidc.sits.logical.shopping.ShoppingOrderCreateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shopping/ordercreate")
public class ShoppingOrderCreateServlet extends APIServlet {
	private static final long serialVersionUID = 5734524359890280856L;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingOrderCreateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ShoppingOrderCreateRequest request = (ShoppingOrderCreateRequest) APIParser.getInstance()
				.parse(apiRequest, ShoppingOrderCreateRequest.class);

		return new ShoppingOrderCreateProcess(request).executeWithStaffToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
