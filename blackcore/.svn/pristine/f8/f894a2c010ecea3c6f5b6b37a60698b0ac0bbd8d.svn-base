package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareItemInsertRequest;
import com.sidc.sits.logical.spare.SpareItemInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/iteminsert")
public class SpareItemInsertServlet extends APIServlet {
	private static final long serialVersionUID = 3172373156107173108L;
	private final static Logger logger = LoggerFactory.getLogger(SpareItemInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareItemInsertRequest request = (SpareItemInsertRequest) APIParser.getInstance().parse(apiRequest,
				SpareItemInsertRequest.class);

		return new SpareItemInsertProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
