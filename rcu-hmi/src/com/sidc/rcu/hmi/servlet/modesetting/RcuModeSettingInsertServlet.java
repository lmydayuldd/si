package com.sidc.rcu.hmi.servlet.modesetting;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.logical.modesetting.ModeSettingInsertProcess;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingInsertRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/insertmodesetting")
public class RcuModeSettingInsertServlet extends APIServlet {
	private static final long serialVersionUID = -584871462239624016L;
	private final static Logger logger = LoggerFactory.getLogger(RcuModeSettingInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(final String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final ModeSettingInsertRequest entity = (ModeSettingInsertRequest) APIParser.getInstance().parses(apiRequest,
				ModeSettingInsertRequest.class);

		return new ModeSettingInsertProcess(entity).execute();
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
