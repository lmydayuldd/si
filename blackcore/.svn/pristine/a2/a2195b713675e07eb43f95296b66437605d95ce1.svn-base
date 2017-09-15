/**
 * 
 */
package com.sidc.blackcore.servlet.rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.ra.logical.api.rcu.RoomRcuInfoProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
@WebServlet("/rcu/roominfo")
public class RoomRcuInfoServlet extends APIServlet {

	private static final long serialVersionUID = 1375676030349019053L;
	private final static Logger logger = LoggerFactory.getLogger(RoomRcuInfoServlet.class);

	public RoomRcuInfoServlet() {
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

		return new RoomRcuInfoProcess().execute();
	}

}
