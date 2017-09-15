package com.sidc.blackcore.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.room.request.RoomCheckInInfoRequest;
import com.sidc.sits.logical.room.CheckInRoomInfoProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/room/checkininfo")
public class CheckInRoomInfoServlet extends APIServlet {
	private static final long serialVersionUID = -8607680032425064900L;
	private final static Logger logger = LoggerFactory.getLogger(CheckInRoomInfoServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RoomCheckInInfoRequest request = (RoomCheckInInfoRequest) APIParser.getInstance().parse(apiRequest,
				RoomCheckInInfoRequest.class);

		return new CheckInRoomInfoProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
