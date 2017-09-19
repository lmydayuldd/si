/**
 * 
 */
package com.sidc.blackcore.servlet.rcu.rcugroup;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupInsertRequest;
import com.sidc.ra.logical.api.rcugroup.RcuGroupInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/insert")
public class RcuGroupInsertServlet extends APIServlet {
	private static final long serialVersionUID = -3408933251461024112L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupInsertServlet.class);

	public RcuGroupInsertServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RcuGroupInsertRequest entity = (RcuGroupInsertRequest) APIParser.getInstance().parse(apiRequest,
				RcuGroupInsertRequest.class);

		return new RcuGroupInsertProcess(entity).execute();
	}

}
