package com.sidc.rcu.hmi.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.modesetting.ModeSettingListProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingListRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/listmodesetting")
public class RcuModeSettingListServlet extends APIServlet {
	private static final long serialVersionUID = -7651992196219692347L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeSettingListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final ModeSettingListRequest entity = (ModeSettingListRequest) APIParser.getInstance().parses(apiRequest,
				ModeSettingListRequest.class);

		return new ModeSettingListProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
