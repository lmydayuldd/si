package com.sidc.blackcore.servlet.sits.tv;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.derex.cm.stb.api.request.StbRemoteControlRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.tvchannel.StbRemoteControlProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/sits/remotecontrol")
public class StbRemoteControlServlet extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8697880777421429901L;
	private final static Logger logger = LoggerFactory.getLogger(StbRemoteControlServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		StbRemoteControlRequest request = (StbRemoteControlRequest) APIParser.getInstance().parse(apiRequest,
				StbRemoteControlRequest.class);

		return new StbRemoteControlProcess(request).executeWithMobileToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
