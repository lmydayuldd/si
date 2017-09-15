package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareOrderListRequest;
import com.sidc.sits.logical.spare.SpareOrderListProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/orderlist")
public class SpareOrderListServlet extends APIServlet {
	private static final long serialVersionUID = 7888798341454427603L;
	private final static Logger logger = LoggerFactory.getLogger(SpareOrderListServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareOrderListRequest request = (SpareOrderListRequest) APIParser.getInstance().parse(apiRequest,
				SpareOrderListRequest.class);

		return new SpareOrderListProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
