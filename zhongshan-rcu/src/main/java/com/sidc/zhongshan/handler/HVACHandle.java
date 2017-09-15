package com.sidc.zhongshan.handler;

import com.google.gson.Gson;
import com.sidc.rcu.connector.bean.command.AirConditionCommander;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.abs.AbstractCommand;
import com.sidc.zhongshan.bean.HVACHex;
import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class HVACHandle extends AbstractCommand {

	public HVACHandle(RCUCommander commander) {
		super(commander);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String handle() throws SiDCException {
		// TODO Auto-generated method stub
		AirConditionCommander airConditionCommander = new Gson().fromJson(super.getCommander().getData().toString(),
				AirConditionCommander.class);
		byte[] bytes = Utils.getInstance().getBytes();
		bytes = HVACHex.command(bytes, airConditionCommander.isPower(), airConditionCommander.getFunction(),
				airConditionCommander.getTemperature(), airConditionCommander.getSpeed(),
				airConditionCommander.getTimer(), airConditionCommander.getAddress());
		return Utils.getInstance().stringData(bytes);
	}

}
