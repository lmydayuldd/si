package com.sidc.rcu.hmi.servlet.demo_rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.commander.RcuStatusProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/status")
public class RcuStatusServlet extends APIServlet {
	private static final long serialVersionUID = 4404590278316835786L;
	private final static Logger logger = LoggerFactory.getLogger(RcuStatusServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(final String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
		return new RcuStatusProcess().execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
