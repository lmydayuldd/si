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
import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupUpdateRoomEnity;
import com.sidc.ra.logical.api.rcugroup.RcuGroupUpdateRoomProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
@WebServlet("/rcu/updateroomrcugroup")
public class RcuGroupUpdateRoomServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5948588296568769912L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupUpdateRoomServlet.class);

	public RcuGroupUpdateRoomServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.service.api.parser.APIServlet#execute(java.lang.String)
	 */
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		RcuGroupUpdateRoomEnity enity = (RcuGroupUpdateRoomEnity) APIParser.getInstance().parse(apiRequest,
				RcuGroupUpdateRoomEnity.class);

		return new RcuGroupUpdateRoomProcess(enity).execute();
	}

}
