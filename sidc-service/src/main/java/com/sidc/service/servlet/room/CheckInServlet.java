package com.sidc.service.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.service.api.parser.APIParser;
import com.sidc.service.api.parser.APIServlet;
import com.sidc.sits.logical.room.CheckInProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/checkin")
public class CheckInServlet extends APIServlet{

	private static final long serialVersionUID = 2143933001417979770L;
	private final static Logger logger = LoggerFactory.getLogger(CheckInServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
		CheckInRequest enity = (CheckInRequest) APIParser.getInstance().parse(apiRequest, CheckInRequest.class);
		
		return new CheckInProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
