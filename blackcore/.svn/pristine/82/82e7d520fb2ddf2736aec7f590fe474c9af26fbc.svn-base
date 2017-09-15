package com.sidc.blackcore.servlet.activity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.activity.request.ActivityBackendOrderListRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.activity.ActivityBackendOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/backend/activity/orderlist")
public class ActivityBackendOrderListServlet extends APIServlet {
	private static final long serialVersionUID = 701682817587341908L;
	private final static Logger logger = LoggerFactory.getLogger(ActivityBackendOrderListServlet.class);

	public ActivityBackendOrderListServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ActivityBackendOrderListRequest enity = (ActivityBackendOrderListRequest) APIParser.getInstance()
				.parse(apiRequest, ActivityBackendOrderListRequest.class);

		return new ActivityBackendOrderListProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
