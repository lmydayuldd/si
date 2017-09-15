package com.sidc.blackcore.servlet.activity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.activity.request.ActivityFeeInsertRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.activity.ActivityFeeInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/activity/feeinsert")
public class ActivityFeeInsertServlet extends APIServlet {

	private static final long serialVersionUID = 4021529544618850394L;
	private final static Logger logger = LoggerFactory.getLogger(ActivityFeeInsertServlet.class);

	public ActivityFeeInsertServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ActivityFeeInsertRequest enity = (ActivityFeeInsertRequest) APIParser.getInstance().parse(apiRequest,
				ActivityFeeInsertRequest.class);

		return new ActivityFeeInsertProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
