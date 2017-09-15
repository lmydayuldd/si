package com.sidc.blackcore.servlet.message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.message.request.NewslettersRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.message.NewslettersProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mobile/newsletters")
public class NewslettersServlet extends APIServlet {

	private static final long serialVersionUID = -5801371003405788856L;
	private final static Logger logger = LoggerFactory.getLogger(NewslettersServlet.class);

	public NewslettersServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final NewslettersRequest enity = (NewslettersRequest) APIParser.getInstance().parse(apiRequest,
				NewslettersRequest.class);

		return new NewslettersProcess(enity).executeWithStaffToken();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
