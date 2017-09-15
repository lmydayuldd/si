package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareItemUpdateRequest;
import com.sidc.sits.logical.spare.SpareItemUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/itemupdate")
public class SpareItemUpdateServlet extends APIServlet {
	private static final long serialVersionUID = 8814353365468241373L;
	private final static Logger logger = LoggerFactory.getLogger(SpareItemUpdateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareItemUpdateRequest request = (SpareItemUpdateRequest) APIParser.getInstance().parse(apiRequest,
				SpareItemUpdateRequest.class);

		return new SpareItemUpdateProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
