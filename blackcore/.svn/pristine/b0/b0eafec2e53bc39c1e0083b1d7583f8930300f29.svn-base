package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareCategoryInsertRequest;
import com.sidc.sits.logical.spare.SpareCategoryInsertProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/categoryinsert")
public class SpareCategoryInsertServlet extends APIServlet {
	private static final long serialVersionUID = 8171615741343701132L;
	private final static Logger logger = LoggerFactory.getLogger(SpareCategoryInsertServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareCategoryInsertRequest request = (SpareCategoryInsertRequest) APIParser.getInstance()
				.parse(apiRequest, SpareCategoryInsertRequest.class);

		return new SpareCategoryInsertProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
