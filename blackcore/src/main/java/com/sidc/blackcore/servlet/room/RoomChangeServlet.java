package com.sidc.blackcore.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.agent.request.RoomChangeRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.room.RoomChangeProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/roomchange")
public class RoomChangeServlet extends APIServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4869781198069942082L;
	private final static Logger logger = LoggerFactory.getLogger(RoomChangeServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
		RoomChangeRequest enity = (RoomChangeRequest) APIParser.getInstance().parse(apiRequest, RoomChangeRequest.class);
		
		new RoomChangeProcess(enity).execute();
		
		return null;
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
