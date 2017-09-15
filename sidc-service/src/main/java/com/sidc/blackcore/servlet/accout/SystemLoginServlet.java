/**
 * 
 */
package com.sidc.blackcore.servlet.accout;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.derex.cm.stb.api.request.SystemUserLogin;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.account.SystemUserLoginProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
@WebServlet("/systemlogin")
public class SystemLoginServlet extends APIServlet {

	private static final long serialVersionUID = -7029710094791081934L;
	private final static Logger logger = LoggerFactory.getLogger(SystemLoginServlet.class);

	public SystemLoginServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.service.api.parser.APIServlet#execute(java.lang.String)
	 */
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {

		SystemUserLogin enity = (SystemUserLogin) APIParser.getInstance().parse(apiRequest, SystemUserLogin.class);

		return new SystemUserLoginProcess(enity).execute();
	}

}
