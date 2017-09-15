package com.sidc.service.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.agent.request.CheckOutRequest;
import com.sidc.service.api.parser.APIParser;
import com.sidc.service.api.parser.APIServlet;
import com.sidc.sits.logical.room.CheckOutProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/checkout")
public class CheckOutServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 200355496467544442L;
	private final static Logger logger = LoggerFactory.getLogger(CheckOutServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		CheckOutRequest enity = (CheckOutRequest) APIParser.getInstance().parse(apiRequest, CheckOutRequest.class);
		return new CheckOutProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
