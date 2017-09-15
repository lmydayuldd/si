package com.sidc.service.servlet.rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.ra.logical.api.RcuRoomStatusProcess;
import com.sidc.service.api.parser.APIServlet;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcuroomstatus")
public class RcuRoomStatusServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -33671904909218008L;
	private final static Logger logger = LoggerFactory.getLogger(RcuRoomStatusServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return new RcuRoomStatusProcess().execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
