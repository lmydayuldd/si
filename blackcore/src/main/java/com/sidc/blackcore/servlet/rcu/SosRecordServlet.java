/**
 * 
 */
package com.sidc.blackcore.servlet.rcu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.parser.APIParser;
import com.sidc.blackcore.api.parser.APIServlet;
import com.sidc.blackcore.api.ra.sosrecord.request.SosRecordRequest;
import com.sidc.ra.logical.api.rcu.sos.SosRecordProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/rcu/sosrecord")
public class SosRecordServlet extends APIServlet {
	private static final long serialVersionUID = 6219297312165799545L;
	private final static Logger logger = LoggerFactory.getLogger(SosRecordServlet.class);

	public SosRecordServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SiDCException, Exception {

		final SosRecordRequest entity = (SosRecordRequest) APIParser.getInstance().parse(apiRequest,
				SosRecordRequest.class);

		return new SosRecordProcess(entity).execute();
	}

}
