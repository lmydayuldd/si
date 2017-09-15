package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareOrderStatusUpdateRequest;
import com.sidc.sits.logical.spare.SpareOrderStatusProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/orderstatus")
public class SpareOrderStatusServlet extends APIServlet {
	private static final long serialVersionUID = 3834173623666979243L;
	private final static Logger logger = LoggerFactory.getLogger(SpareOrderStatusServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareOrderStatusUpdateRequest request = (SpareOrderStatusUpdateRequest) APIParser.getInstance()
				.parse(apiRequest, SpareOrderStatusUpdateRequest.class);

		return new SpareOrderStatusProcess(request).executeByAuthToken(req.getServletPath());
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
