package com.sidc.blackcore.servlet.laundryservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryReturnTypeUpdateRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.laundryservice.LaundryReturnTypeUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/laundryservice/returntypeupdate")
public class LaundryReturnTypeUpdateServlet extends APIServlet {
	private static final long serialVersionUID = -6687272447989066451L;
	private final static Logger logger = LoggerFactory.getLogger(LaundryReturnTypeUpdateServlet.class);

	public LaundryReturnTypeUpdateServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final LaundryReturnTypeUpdateRequest enity = (LaundryReturnTypeUpdateRequest) APIParser.getInstance()
				.parse(apiRequest, LaundryReturnTypeUpdateRequest.class);

		return new LaundryReturnTypeUpdateProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
