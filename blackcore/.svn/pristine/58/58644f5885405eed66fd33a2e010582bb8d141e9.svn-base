/**
 * 
 */
package com.sidc.blackcore.servlet.rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.request.RoomModuleRequest;
import com.sidc.ra.logical.api.ZhongshanRcuModuleInitialProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
@WebServlet("/zhongshanrcumoduleinitial")
public class ZhongshanRcuModuleInitialServlet extends APIServlet {

	private static final long serialVersionUID = 5749364628907781557L;
	private final static Logger logger = LoggerFactory.getLogger(ZhongshanRcuModuleInitialServlet.class);

	/**
	 * 
	 */
	public ZhongshanRcuModuleInitialServlet() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.service.api.parser.APIServlet#execute(java.lang.String)
	 */
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		RoomModuleRequest enity = (RoomModuleRequest) APIParser.getInstance().parse(apiRequest,
				RoomModuleRequest.class);

		return new ZhongshanRcuModuleInitialProcess(enity).execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.service.api.parser.APIServlet#initial(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
