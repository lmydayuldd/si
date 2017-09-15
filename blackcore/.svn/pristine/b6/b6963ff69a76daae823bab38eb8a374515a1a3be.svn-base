package com.sidc.blackcore.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomallocation.request.HandlerTransferRequest;
import com.sidc.sits.logical.room.HandlerTransferProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/handlertransfer")
public class HandlerTransferServlet extends APIServlet {

	private static final long serialVersionUID = 8849375425646991928L;
	private final static Logger logger = LoggerFactory.getLogger(HandlerTransferServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final HandlerTransferRequest enity = (HandlerTransferRequest) APIParser.getInstance().parse(apiRequest,
				HandlerTransferRequest.class);

		return new HandlerTransferProcess(enity).executeWithStaffToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
