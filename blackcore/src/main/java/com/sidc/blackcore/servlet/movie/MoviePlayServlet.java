package com.sidc.blackcore.servlet.movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.movie.request.MoviePlayRequest;
import com.sidc.sits.logical.movie.MoviePlayProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/sits/movieplay")
public class MoviePlayServlet extends APIServlet {
	private static final long serialVersionUID = 1520142595902029715L;
	private final static Logger logger = LoggerFactory.getLogger(MoviePlayServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		final MoviePlayRequest enity = (MoviePlayRequest) APIParser.getInstance().parse(apiRequest,
				MoviePlayRequest.class);

		return new MoviePlayProcess(enity).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}
