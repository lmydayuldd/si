package com.sidc.blackcore.servlet.spare;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.spare.request.SpareOrderCreateRequest;
import com.sidc.sits.logical.spare.SpareOrderCreateProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/spare/ordercreate")
public class SpareOrderCreateServlet extends APIServlet {
	private static final long serialVersionUID = 5156649744544889794L;
	private final static Logger logger = LoggerFactory.getLogger(SpareOrderCreateServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final SpareOrderCreateRequest request = (SpareOrderCreateRequest) APIParser.getInstance().parse(apiRequest,
				SpareOrderCreateRequest.class);

		return new SpareOrderCreateProcess(request).executeWithMobileToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
