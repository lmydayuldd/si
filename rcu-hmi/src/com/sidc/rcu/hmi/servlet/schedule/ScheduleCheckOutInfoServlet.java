package com.sidc.rcu.hmi.servlet.schedule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.schedule.ScheduleCheckOutInfoProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/schedule/checkupdateinfo")
public class ScheduleCheckOutInfoServlet extends APIServlet {
	private static final long serialVersionUID = -6331123081915060961L;
	private final static Logger logger = LoggerFactory.getLogger(ScheduleCheckOutInfoServlet.class);

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return new ScheduleCheckOutInfoProcess().execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
