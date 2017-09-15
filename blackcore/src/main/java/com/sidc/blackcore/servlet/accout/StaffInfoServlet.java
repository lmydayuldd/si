package com.sidc.blackcore.servlet.accout;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.account.request.StaffInfoRequest;
import com.sidc.sits.logical.account.StaffInfoProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/staffinfo")
public class StaffInfoServlet extends APIServlet {

	private static final long serialVersionUID = -1787994527317997323L;
	private final static Logger logger = LoggerFactory.getLogger(StaffInfoServlet.class);

	public StaffInfoServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final StaffInfoRequest enity = (StaffInfoRequest) APIParser.getInstance().parse(apiRequest,
				StaffInfoRequest.class);

		return new StaffInfoProcess(enity).executeWithStaffToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
