package com.sidc.quartz.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.quartz.abs.APIParser;
import com.sidc.quartz.abs.APIServlet;
import com.sidc.quartz.api.request.ScheduleInfoRequest;
import com.sidc.quartz.logical.api.ScheduleInfoProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/schedule/info")
public class ScheduleInfoServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1782729413444875198L;
	private final static Logger logger = LoggerFactory.getLogger(ScheduleInfoServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ScheduleInfoRequest enity = (ScheduleInfoRequest) APIParser.getInstance().parse(apiRequest,
				ScheduleInfoRequest.class);
		
		return new ScheduleInfoProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
