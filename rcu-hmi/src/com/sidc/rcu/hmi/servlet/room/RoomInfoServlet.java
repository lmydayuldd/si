package com.sidc.rcu.hmi.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.room.RoomInfoProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/roominfo")
public class RoomInfoServlet extends APIServlet {

	private static final long serialVersionUID = 8038656850564059230L;
	private final static Logger logger = LoggerFactory.getLogger(RoomInfoServlet.class);

	public RoomInfoServlet() throws SiDCException, Exception {
	}

	@Override
	protected Object execute(final String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		return new RoomInfoProcess().execute();
	}

	@Override
	protected void initial(final HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());

	}

}
