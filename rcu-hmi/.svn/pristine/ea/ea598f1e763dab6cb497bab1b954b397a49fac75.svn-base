package com.sidc.rcu.hmi.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sidc.rcu.hmi.api.parser.APIServlet;
import com.sidc.rcu.hmi.bean.receiver.UdpRreceiveBean;
import com.sidc.rcu.hmi.udp.receiver.RcuServiceInfoReceiver;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

@WebServlet("/test")
public class testUdpServlet extends APIServlet {

	private static final long serialVersionUID = 8362543650111464719L;
	private final static Logger logger = LoggerFactory.getLogger(testUdpServlet.class);

	@Override
	protected Object execute(String udpRequest) throws SiDCException, Exception {
		// TODO Auto-generated method stub
//		new RcuRoomStatusInitial("/listroomrcu").execute();
		Gson gson = new Gson();

		UdpRreceiveBean entity = gson.fromJson(udpRequest, UdpRreceiveBean.class);
		new RcuServiceInfoReceiver(entity.getRoomNo(), entity.getData()).execute();
		return null;
	}

	@Override
	protected void initial(HttpServletRequest req) throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
