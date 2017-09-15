package com.sidc.blackcore.servlet.token;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.token.request.MobilePrivateTokenInsertRequest;
import com.sidc.sits.logical.token.MobilePrivateTokenInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mobile/token")
public class MobilePrivateTokenInsertServlet extends APIServlet {
	private static final long serialVersionUID = -5851861673726570741L;
	private final static Logger logger = LoggerFactory.getLogger(MobilePrivateTokenInsertServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final MobilePrivateTokenInsertRequest enity = (MobilePrivateTokenInsertRequest) APIParser.getInstance()
				.parse(apiRequest, MobilePrivateTokenInsertRequest.class);

		return new MobilePrivateTokenInsertProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
