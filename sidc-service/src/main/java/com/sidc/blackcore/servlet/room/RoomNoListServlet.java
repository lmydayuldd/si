package com.sidc.blackcore.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.room.RoomNoListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/roomnolist")
public class RoomNoListServlet extends APIServlet {

	private static final long serialVersionUID = 2143933001417979770L;
	private final static Logger logger = LoggerFactory.getLogger(RoomNoListServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {

		return new RoomNoListProcess().execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
