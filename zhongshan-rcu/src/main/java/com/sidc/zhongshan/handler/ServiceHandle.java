package com.sidc.zhongshan.handler;

import com.google.gson.Gson;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.bean.command.ServiceCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.abs.AbstractCommand;
import com.sidc.zhongshan.bean.ServiceHex;
import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class ServiceHandle extends AbstractCommand {

	public ServiceHandle(RCUCommander commander) {
		super(commander);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String handle() throws SiDCException {
		// TODO Auto-generated method stub
		ServiceCommander serviceCommander = new Gson().fromJson(super.getCommander().getData().toString(),
				ServiceCommander.class);
		byte[] bytes = Utils.getInstance().getBytes();
		bytes = ServiceHex.command(bytes, getCommander().getKeycode(), serviceCommander.getValue());
		return Utils.getInstance().stringData(bytes);
	}

}
