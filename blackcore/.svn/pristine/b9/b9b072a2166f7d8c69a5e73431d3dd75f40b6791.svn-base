package com.sidc.blackcore.servlet.sits.bill;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.sits.bill.request.BillReviewRequest;
import com.sidc.sits.logical.bill.BillReviewProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * 
 * @author Allen Huang
 *
 */
@WebServlet("/sits/billreview")
public class BillReviewServlet  extends APIServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -620266073781115191L;
	private final static Logger logger = LoggerFactory.getLogger(BillReviewServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		BillReviewRequest request = (BillReviewRequest) APIParser.getInstance().parse(apiRequest, BillReviewRequest.class);
		
		return new BillReviewProcess(request).execute();
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
