package com.sidc.zhongshan.handler;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.abs.AbstractCommand;
import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class AskHandle extends AbstractCommand {

	public AskHandle(RCUCommander commander) {
		super(commander);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String handle() throws SiDCException {
		// TODO Auto-generated method stub
		byte[] bytes = new byte[40];
		for (int i = 0; i < 40; i++) {
			int preset = 0x00;
			bytes[i] = (byte) preset;
		}
		
		byte[] getbyte = new byte[1];
		getbyte = Utils.getInstance().hexToBytes("0x01");
		bytes[0] = getbyte[0];
		
		return Utils.getInstance().stringData(bytes);
	}

}
