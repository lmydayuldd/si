package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareCategoryRequest;
import com.sidc.sits.logical.spare.SpareCategoryProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/category")
public class SpareCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 2716102992485707756L;
	private final static Logger logger = LoggerFactory.getLogger(SpareCategoryServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareCategoryRequest request = (SpareCategoryRequest) APIParser.getInstance().parse(apiRequest,
				SpareCategoryRequest.class);

		return new SpareCategoryProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
