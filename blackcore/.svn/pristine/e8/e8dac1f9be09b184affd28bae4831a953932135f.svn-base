package com.sidc.blackcore.servlet.laundryservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.laundry.request.LaundryReturnTypeInsertRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.laundryservice.LaundryReturnTypeInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/laundryservice/returntypeinsert")
public class LaundryReturnTypeInsertServlet extends APIServlet {

	private static final long serialVersionUID = -6514673271887636588L;
	private final static Logger logger = LoggerFactory.getLogger(LaundryReturnTypeInsertServlet.class);

	public LaundryReturnTypeInsertServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final LaundryReturnTypeInsertRequest enity = (LaundryReturnTypeInsertRequest) APIParser.getInstance()
				.parse(apiRequest, LaundryReturnTypeInsertRequest.class);

		return new LaundryReturnTypeInsertProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
