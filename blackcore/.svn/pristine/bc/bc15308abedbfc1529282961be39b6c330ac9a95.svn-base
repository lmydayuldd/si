package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareBackendOrderListRequest;
import com.sidc.sits.logical.spare.SpareBackendOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/backend/spare/orderlist")
public class SpareBackendOrderListServlet extends APIServlet {
	private static final long serialVersionUID = -2194148352389425102L;
	private final static Logger logger = LoggerFactory.getLogger(SpareBackendOrderListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareBackendOrderListRequest request = (SpareBackendOrderListRequest) APIParser.getInstance()
				.parse(apiRequest, SpareBackendOrderListRequest.class);

		return new SpareBackendOrderListProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
