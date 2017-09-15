package com.sidc.blackcore.servlet.activity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.activity.request.ActivityTypeRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.activity.ActivityTypeProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/activity/type")
public class ActivityTypeServlet extends APIServlet {

	private static final long serialVersionUID = -8003105608922354992L;
	private final static Logger logger = LoggerFactory.getLogger(ActivityTypeServlet.class);

	public ActivityTypeServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ActivityTypeRequest enity = (ActivityTypeRequest) APIParser.getInstance().parse(apiRequest,
				ActivityTypeRequest.class);

		return new ActivityTypeProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
