/**
 * 
 */
package com.sidc.blackcore.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.room.RoomStatusListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/listroomstatus")
public class RoomStatusListServlet extends APIServlet {
	private static final long serialVersionUID = 7022091709020204604L;
	private final static Logger logger = LoggerFactory.getLogger(RoomStatusListServlet.class);

	public RoomStatusListServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return new RoomStatusListProcess().execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());

	}

}
