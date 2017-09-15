package com.sidc.blackcore.servlet.activity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.activity.request.ActivityOrderListRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.activity.ActivityOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/activity/orderlist")
public class ActivityOrderListServlet extends APIServlet {
	private static final long serialVersionUID = -3693242451681043481L;
	private final static Logger logger = LoggerFactory.getLogger(ActivityOrderListServlet.class);

	public ActivityOrderListServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ActivityOrderListRequest enity = (ActivityOrderListRequest) APIParser.getInstance().parse(apiRequest,
				ActivityOrderListRequest.class);

		return new ActivityOrderListProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
