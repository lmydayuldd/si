/**
 * 
 */
package com.sidc.quartz.api.rcu;

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

@WebServlet("/schedule/rcu/checkoutupdate")
public class RcuScheduleCheckOutUpdateServlet extends APIServlet {

	private static final long serialVersionUID = 1833109806470110967L;
	private final static Logger logger = LoggerFactory.getLogger(RcuScheduleCheckOutUpdateServlet.class);

	public RcuScheduleCheckOutUpdateServlet() {
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
