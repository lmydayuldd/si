/**
 * 
 */
package com.sidc.blackcore.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.response.RoomRCUStatusEnity;
import com.sidc.ra.logical.api.RoomsRCUStatusProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
@WebServlet("/listroomrcu")
public class RoomsRCUStatusServlet extends APIServlet {

	private static final long serialVersionUID = 8955655428189902255L;
	private final static Logger logger = LoggerFactory.getLogger(CheckInServlet.class);

	public RoomsRCUStatusServlet() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.service.api.parser.APIServlet#execute(java.lang.String)
	 */
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return new RoomsRCUStatusProcess().execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.service.api.parser.APIServlet#initial(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());

	}

}
