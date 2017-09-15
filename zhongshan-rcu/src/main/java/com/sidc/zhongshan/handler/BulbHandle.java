package com.sidc.zhongshan.handler;

import com.google.gson.Gson;
import com.sidc.raudp.bean.PositionBean;
import com.sidc.rcu.connector.bean.command.BulbCommander;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.abs.AbstractCommand;
import com.sidc.zhongshan.bean.BulbHex;
import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class BulbHandle extends AbstractCommand {

	private PositionBean pos;

	public BulbHandle(RCUCommander commander, PositionBean pos) {
		super(commander);
		this.pos = pos;
	}

	@Override
	public String handle() throws SiDCException {
		// TODO Auto-generated method stub
		BulbCommander bulbCommander = new Gson().fromJson(super.getCommander().getData().toString(),
				BulbCommander.class);
		byte[] bytes = Utils.getInstance().getBytes();
		if (pos.getCircuit() > -1) {
			bytes = BulbHex.Command(bytes, pos.getAddress(), pos.getCircuit(), bulbCommander.getValue());
		} else {
			bytes = BulbHex.Command(bytes, pos.getAddress(), bulbCommander.getValue());
		}
		return Utils.getInstance().stringData(bytes);
	}

}
