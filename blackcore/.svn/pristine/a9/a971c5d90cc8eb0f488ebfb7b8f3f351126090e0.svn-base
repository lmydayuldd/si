/**
 * 
 */
package com.sidc.blackcore.servlet.rcugroup;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupNewRoomEnity;
import com.sidc.ra.logical.api.rcugroup.RcuGroupNewRoomProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
@WebServlet("/rcu/buildroomrcugroup")
public class RcuGroupNewRoomServlet extends APIServlet {

	private static final long serialVersionUID = 1375676030349019053L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupNewRoomServlet.class);

	public RcuGroupNewRoomServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.service.api.parser.APIServlet#execute(java.lang.String)
	 */
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		RcuGroupNewRoomEnity enity = (RcuGroupNewRoomEnity) APIParser.getInstance().parse(apiRequest,
				RcuGroupNewRoomEnity.class);

		return new RcuGroupNewRoomProcess(enity).execute();
	}

}
