package com.sidc.rcu.hmi.servlet.rcugourp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.group.request.GroupDeleteRequest;
import com.sidc.rcu.hmi.logical.rcugroup.GroupDeleteProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/group/delete")
public class GroupDeleteServlet extends APIServlet {
	private static final long serialVersionUID = -6519139633438760541L;
	private final static Logger logger = LoggerFactory.getLogger(GroupDeleteServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final GroupDeleteRequest entity = (GroupDeleteRequest) APIParser.getInstance().parses(apiRequest,
				GroupDeleteRequest.class);

		return new GroupDeleteProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
