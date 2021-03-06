/**
 * 
 */
package com.sidc.blackcore.servlet.rcugroup;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupDeleteRequest;
import com.sidc.ra.logical.api.rcugroup.RcuGroupDeleteProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/delete")
public class RcuGroupDeleteServlet extends APIServlet {
	private static final long serialVersionUID = 3101835819942427868L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupDeleteServlet.class);

	public RcuGroupDeleteServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RcuGroupDeleteRequest entity = (RcuGroupDeleteRequest) APIParser.getInstance().parse(apiRequest,
				RcuGroupDeleteRequest.class);

		return new RcuGroupDeleteProcess(entity).execute();
	}

}
