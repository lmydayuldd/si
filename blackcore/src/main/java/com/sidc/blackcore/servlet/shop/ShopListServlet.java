package com.sidc.blackcore.servlet.shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.shop.request.ShopListRequest;
import com.sidc.sits.logical.shop.ShopListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/shoplist")
public class ShopListServlet extends APIServlet {
	private static final long serialVersionUID = -6321350741006421720L;
	private final static Logger logger = LoggerFactory.getLogger(ShopListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final ShopListRequest enity = (ShopListRequest) APIParser.getInstance().parse(apiRequest,
				ShopListRequest.class);

		return new ShopListProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
