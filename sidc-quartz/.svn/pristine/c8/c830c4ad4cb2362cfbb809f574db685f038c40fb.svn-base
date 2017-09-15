package com.sidc.quartz.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.quartz.abs.APIParser;
import com.sidc.quartz.abs.APIServlet;
import com.sidc.quartz.api.request.ScheduleUpdateInfoRequest;
import com.sidc.quartz.logical.api.ScheduleUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/schedule/update")
public class ScheduleUpdateServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4857173589496305616L;
	private final static Logger logger = LoggerFactory.getLogger(ScheduleUpdateServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		ScheduleUpdateInfoRequest request = (ScheduleUpdateInfoRequest) APIParser.getInstance().parse(apiRequest,
				ScheduleUpdateInfoRequest.class);
		
		return new ScheduleUpdateProcess(request).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
