/**
 * 
 */
package com.sidc.quartz.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.quartz.abs.APIParser;
import com.sidc.quartz.abs.APIServlet;
import com.sidc.quartz.api.request.ScheduleStatusRequest;
import com.sidc.quartz.logical.api.ScheduleStatusProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/schedule/status")
public class ScheduleStatusServlet extends APIServlet {

	private static final long serialVersionUID = 1833109806470110967L;
	private final static Logger logger = LoggerFactory.getLogger(ScheduleStatusServlet.class);

	public ScheduleStatusServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ScheduleStatusRequest enity = (ScheduleStatusRequest) APIParser.getInstance().parse(apiRequest,
				ScheduleStatusRequest.class);

		return new ScheduleStatusProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
