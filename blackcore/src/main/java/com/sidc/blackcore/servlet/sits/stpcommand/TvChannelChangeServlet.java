package com.sidc.blackcore.servlet.sits.stpcommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.tvchannel.request.TvChannelChangeRequest;
import com.sidc.sits.logical.tvchannel.TvChannelChangeProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/stb/tvchannelchange")
public class TvChannelChangeServlet extends APIServlet {
	private static final long serialVersionUID = 3324355301194496992L;
	private final static Logger logger = LoggerFactory.getLogger(TvChannelChangeServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final TvChannelChangeRequest enity = (TvChannelChangeRequest) APIParser.getInstance().parse(apiRequest,
				TvChannelChangeRequest.class);

		return new TvChannelChangeProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
