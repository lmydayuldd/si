package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareItemRequest;
import com.sidc.sits.logical.spare.SpareItemProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/item")
public class SpareItemServlet extends APIServlet {
	private static final long serialVersionUID = -4235196102235078995L;
	private final static Logger logger = LoggerFactory.getLogger(SpareItemServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareItemRequest request = (SpareItemRequest) APIParser.getInstance().parse(apiRequest,
				SpareItemRequest.class);

		return new SpareItemProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
