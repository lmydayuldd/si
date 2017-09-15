package com.sidc.blackcore.servlet.laundryservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryBackendOrderListRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.laundryservice.LaundryBackendOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/backend/laundryservice/orderlist")
public class LaundryBackendOrderListServlet extends APIServlet {
	private static final long serialVersionUID = -4107500189435235555L;
	private final static Logger logger = LoggerFactory.getLogger(LaundryBackendOrderListServlet.class);

	public LaundryBackendOrderListServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final LaundryBackendOrderListRequest enity = (LaundryBackendOrderListRequest) APIParser.getInstance()
				.parse(apiRequest, LaundryBackendOrderListRequest.class);

		return new LaundryBackendOrderListProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
