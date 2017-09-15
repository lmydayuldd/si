package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareCategoryUpdateRequest;
import com.sidc.sits.logical.spare.SpareCategoryUpdateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/categoryupdate")
public class SpareCategoryUpdateServlet extends APIServlet {
	private static final long serialVersionUID = -7518864364217896333L;
	private final static Logger logger = LoggerFactory.getLogger(SpareCategoryUpdateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareCategoryUpdateRequest request = (SpareCategoryUpdateRequest) APIParser.getInstance()
				.parse(apiRequest, SpareCategoryUpdateRequest.class);

		return new SpareCategoryUpdateProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
