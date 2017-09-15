package com.sidc.rcu.hmi.servlet.rcugourp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.groupdevice.request.GroupDeviceRequest;
import com.sidc.rcu.hmi.logical.rcugroup.GroupDeviceListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/device")
public class GroupDeviceListServlet extends APIServlet {

	private static final long serialVersionUID = -1708533425479864795L;
	private final static Logger logger = LoggerFactory.getLogger(GroupDeviceListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final GroupDeviceRequest entity = (GroupDeviceRequest) APIParser.getInstance().parses(apiRequest,
				GroupDeviceRequest.class);

		return new GroupDeviceListProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
