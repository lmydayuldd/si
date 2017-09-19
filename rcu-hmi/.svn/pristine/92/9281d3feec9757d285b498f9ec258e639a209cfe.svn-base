package com.sidc.rcu.hmi.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.room.RcuRoomListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/rooms")
public class RcuRoomsServlet extends APIServlet {

	private static final long serialVersionUID = -2144015934401471275L;
	private final static Logger logger = LoggerFactory.getLogger(RcuRoomsServlet.class);

	public RcuRoomsServlet() throws SiDCException, Exception {
	}

	@Override
	protected Object execute(final String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		return new RcuRoomListProcess().execute();
	}

	@Override
	protected void initial(final HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());

	}

}
