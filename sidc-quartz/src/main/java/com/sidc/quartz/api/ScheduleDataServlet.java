package com.sidc.quartz.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.quartz.abs.APIParser;
import com.sidc.quartz.abs.APIServlet;
import com.sidc.quartz.api.request.ScheduleDataRequest;
import com.sidc.quartz.logical.api.ScheduleDataProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/schedule/data")
public class ScheduleDataServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1782729413444875198L;
	private final static Logger logger = LoggerFactory.getLogger(ScheduleDataServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final ScheduleDataRequest enity = (ScheduleDataRequest) APIParser.getInstance().parse(apiRequest,
				ScheduleDataRequest.class);
		
		return new ScheduleDataProcess(enity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
