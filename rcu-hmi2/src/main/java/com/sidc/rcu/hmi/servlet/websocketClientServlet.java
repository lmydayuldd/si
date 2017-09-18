package com.sidc.rcu.hmi.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.utils.exception.SiDCException;

@WebServlet("/webcocketclient")
public class websocketClientServlet extends APIServlet {
	private static final long serialVersionUID = 5582745075781598298L;

	@Override
	protected Object execute(String apiRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

}
