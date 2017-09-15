package com.sidc.blackcore.servlet.laundryservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryTypeUpdateRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.laundryservice.LaundryTypeUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/laundryservice/typeupdate")
public class LaundryTypeUpdateServlet extends APIServlet {
	private static final long serialVersionUID = -6091214384353318442L;
	private final static Logger logger = LoggerFactory.getLogger(LaundryTypeUpdateServlet.class);

	public LaundryTypeUpdateServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final LaundryTypeUpdateRequest enity = (LaundryTypeUpdateRequest) APIParser.getInstance().parse(apiRequest,
				LaundryTypeUpdateRequest.class);

		return new LaundryTypeUpdateProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
