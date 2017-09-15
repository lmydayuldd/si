/**
 * 
 */
package com.sidc.quartz.api.sits;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.quartz.abs.APIParser;
import com.sidc.quartz.abs.APIServlet;
import com.sidc.quartz.api.request.rcu.RcuScheduleCheckOutUpdateRequest;
import com.sidc.quartz.logical.rcu.api.RcuScheduleCheckOutUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/schedule/sits/flight")
public class FlightInfoServlet extends APIServlet {

	private static final long serialVersionUID = 9130858091155565915L;
	private final static Logger logger = LoggerFactory.getLogger(FlightInfoServlet.class);

	public FlightInfoServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final RcuScheduleCheckOutUpdateRequest enity = (RcuScheduleCheckOutUpdateRequest) APIParser.getInstance()
				.parse(apiRequest, RcuScheduleCheckOutUpdateRequest.class);

		return new RcuScheduleCheckOutUpdateProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
