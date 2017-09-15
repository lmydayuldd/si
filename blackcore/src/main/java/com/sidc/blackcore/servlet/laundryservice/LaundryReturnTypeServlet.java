package com.sidc.blackcore.servlet.laundryservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryReturnTypeRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.laundryservice.LaundryReturnTypeProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/laundryservice/returntype")
public class LaundryReturnTypeServlet extends APIServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7025101857749930574L;
	private final static Logger logger = LoggerFactory.getLogger(LaundryReturnTypeServlet.class);

	public LaundryReturnTypeServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final LaundryReturnTypeRequest enity = (LaundryReturnTypeRequest) APIParser.getInstance().parse(apiRequest,
				LaundryReturnTypeRequest.class);

		return new LaundryReturnTypeProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
