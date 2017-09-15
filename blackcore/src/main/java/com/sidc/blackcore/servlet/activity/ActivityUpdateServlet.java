package com.sidc.blackcore.servlet.activity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.activity.request.ActivityUpdateRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.activity.ActivityUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/activity/update")
public class ActivityUpdateServlet extends APIServlet {
	private static final long serialVersionUID = 1179895717332482997L;
	private final static Logger logger = LoggerFactory.getLogger(ActivityUpdateServlet.class);

	public ActivityUpdateServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ActivityUpdateRequest enity = (ActivityUpdateRequest) APIParser.getInstance().parse(apiRequest,
				ActivityUpdateRequest.class);

		return new ActivityUpdateProcess(enity).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
