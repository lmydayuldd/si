package com.sidc.blackcore.servlet.activity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.activity.request.ActivityTypeInsertRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.activity.ActivityTypeInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/activity/typeinsert")
public class ActivityTypeInsertServlet extends APIServlet {

	private static final long serialVersionUID = -5403560237885892232L;
	private final static Logger logger = LoggerFactory.getLogger(ActivityTypeInsertServlet.class);

	public ActivityTypeInsertServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ActivityTypeInsertRequest enity = (ActivityTypeInsertRequest) APIParser.getInstance().parse(apiRequest,
				ActivityTypeInsertRequest.class);

		return new ActivityTypeInsertProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
