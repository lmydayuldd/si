/**
 * 
 */
package com.sidc.blackcore.servlet.room;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.roomallocation.request.RoomAllocationRequest;
import com.sidc.sits.logical.room.RoomAllocationProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Joe
 *
 */
@WebServlet("/roomallocation")
public class RoomAllocationServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7081259249325391178L;
	private final static Logger logger = LoggerFactory.getLogger(RoomAllocationServlet.class);

	public RoomAllocationServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final RoomAllocationRequest entity = (RoomAllocationRequest) APIParser.getInstance().parse(apiRequest,
				RoomAllocationRequest.class);

		return new RoomAllocationProcess(entity).executeWithStaffToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
