package com.sidc.rcu.hmi.servlet.rcugourp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.group.request.GroupInsertRequest;
import com.sidc.rcu.hmi.logical.rcugroup.GroupInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/insert")
public class GroupInsertServlet extends APIServlet {
	private static final long serialVersionUID = -7573949512269770775L;
	private final static Logger logger = LoggerFactory.getLogger(GroupInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final GroupInsertRequest entity = (GroupInsertRequest) APIParser.getInstance().parses(apiRequest,
				GroupInsertRequest.class);

		return new GroupInsertProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
