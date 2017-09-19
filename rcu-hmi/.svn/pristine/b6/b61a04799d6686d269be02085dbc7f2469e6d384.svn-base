package com.sidc.rcu.hmi.servlet.rcugourp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.groupdevice.request.GroupDeviceInsertRequest;
import com.sidc.rcu.hmi.logical.rcugroup.GroupDeviceInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/device/insert")
public class GroupDeviceInsertServlet extends APIServlet {

	private static final long serialVersionUID = 4341900961843472595L;
	private final static Logger logger = LoggerFactory.getLogger(GroupDeviceInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final GroupDeviceInsertRequest entity = (GroupDeviceInsertRequest) APIParser.getInstance().parses(apiRequest,
				GroupDeviceInsertRequest.class);

		return new GroupDeviceInsertProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
