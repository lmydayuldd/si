/**
 * 
 */
package com.sidc.blackcore.servlet.rcu.rcugroup;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcugroup.request.RoomRcuGroupUpdateRequest;
import com.sidc.ra.logical.api.rcugroup.RoomRcuGroupUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/roomrcugroup/update")
public class RoomRcuGroupUpdateServlet extends APIServlet {
	private static final long serialVersionUID = -9015628456906161932L;
	private final static Logger logger = LoggerFactory.getLogger(RoomRcuGroupUpdateServlet.class);

	public RoomRcuGroupUpdateServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RoomRcuGroupUpdateRequest entity = (RoomRcuGroupUpdateRequest) APIParser.getInstance().parse(apiRequest,
				RoomRcuGroupUpdateRequest.class);

		return new RoomRcuGroupUpdateProcess(entity).execute();
	}

}
