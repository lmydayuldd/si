package com.sidc.service.servlet.rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.ra.logical.api.RcuRoomServiceInfoProcess;
import com.sidc.service.api.parser.APIServlet;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Joe
 *
 */
@WebServlet("/rcuroomserviceinfo")
public class RcuRoomServiceInfoServlet extends APIServlet {
	private static final long serialVersionUID = 6079181778108107041L;
	private final static Logger logger = LoggerFactory.getLogger(RcuRoomServiceInfoServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		// APIParser.getInstance().parse(apiRequest, null);
		return new RcuRoomServiceInfoProcess().execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
