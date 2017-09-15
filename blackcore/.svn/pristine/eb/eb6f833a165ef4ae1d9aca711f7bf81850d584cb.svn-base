/**
 * 
 */
package com.sidc.blackcore.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuModeSettingListRequest;
import com.sidc.ra.logical.api.rcumodesetting.RcuModeSettingListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/groupsearch")
public class RcuGroupSearchServlet extends APIServlet {

	private static final long serialVersionUID = 8754880041118166688L;
	private final static Logger logger = LoggerFactory.getLogger(RcuGroupSearchServlet.class);

	public RcuGroupSearchServlet() {
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final RcuModeSettingListRequest enity = (RcuModeSettingListRequest) APIParser.getInstance().parse(apiRequest,
				RcuModeSettingListRequest.class);

		return new RcuModeSettingListProcess(enity).execute();
	}
}
