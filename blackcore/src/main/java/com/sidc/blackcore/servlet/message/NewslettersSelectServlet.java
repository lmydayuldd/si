package com.sidc.blackcore.servlet.message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.mobile.message.request.NewslettersSelectRequest;
import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.sits.logical.message.NewslettersSelectProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/mobile/newslettersselect")
public class NewslettersSelectServlet extends APIServlet {
	private static final long serialVersionUID = -1621883518499188285L;
	private final static Logger logger = LoggerFactory.getLogger(NewslettersSelectServlet.class);

	public NewslettersSelectServlet() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final NewslettersSelectRequest enity = (NewslettersSelectRequest) APIParser.getInstance().parse(apiRequest,
				NewslettersSelectRequest.class);

		return new NewslettersSelectProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
