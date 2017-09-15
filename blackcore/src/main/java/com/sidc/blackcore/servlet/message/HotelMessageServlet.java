package com.sidc.blackcore.servlet.message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.message.request.HotelMessageRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.message.HotelMessageProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mobile/hotelmessage")
public class HotelMessageServlet extends APIServlet {

	private static final long serialVersionUID = -4175382692345173318L;
	private final static Logger logger = LoggerFactory.getLogger(HotelMessageServlet.class);

	public HotelMessageServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final HotelMessageRequest enity = (HotelMessageRequest) APIParser.getInstance().parse(apiRequest,
				HotelMessageRequest.class);

		return new HotelMessageProcess(enity).executeWithStaffToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
