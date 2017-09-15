package com.sidc.quartz.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.quartz.abs.APIParser;
import com.sidc.quartz.abs.APIServlet;
import com.sidc.quartz.api.request.ScheduleCommandRequest;
import com.sidc.quartz.logical.api.ScheduleCommandProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/schedule/command")
public class ScheduleCommandServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1213769713424858301L;
	private final static Logger logger = LoggerFactory.getLogger(ScheduleCommandServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		ScheduleCommandRequest request = (ScheduleCommandRequest) APIParser.getInstance().parse(apiRequest,
				ScheduleCommandRequest.class);

		return new ScheduleCommandProcess(request).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
