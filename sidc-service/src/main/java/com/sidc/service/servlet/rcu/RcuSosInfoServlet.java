package com.sidc.service.servlet.rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.ra.logical.api.RcuSOSInfoProcess;
import com.sidc.service.api.parser.APIServlet;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Joe
 *
 */
@WebServlet("/rcusosinfo")
public class RcuSosInfoServlet extends APIServlet {

	private static final long serialVersionUID = -9152584337314355519L;
	private final static Logger logger = LoggerFactory.getLogger(RcuSosInfoServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		// APIParser.getInstance().parse(apiRequest, null);
		return new RcuSOSInfoProcess().execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
