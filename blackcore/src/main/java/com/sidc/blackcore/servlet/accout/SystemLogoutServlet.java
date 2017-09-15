package com.sidc.blackcore.servlet.accout;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.account.request.SystemUserLogoutRequest;
import com.sidc.sits.logical.account.SystemUserLogoutProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/systemlogout")
public class SystemLogoutServlet extends APIServlet {

	private static final long serialVersionUID = 4981189665047389260L;
	private final static Logger logger = LoggerFactory.getLogger(SystemLogoutServlet.class);

	public SystemLogoutServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SystemUserLogoutRequest enity = (SystemUserLogoutRequest) APIParser.getInstance().parse(apiRequest,
				SystemUserLogoutRequest.class);

		return new SystemUserLogoutProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
