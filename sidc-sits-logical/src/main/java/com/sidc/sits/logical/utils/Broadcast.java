package com.sidc.sits.logical.utils;

import com.google.gson.Gson;
import com.sidc.rcu.connector.bean.receiver.RCUReceiverInfo;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.net.UDPClientBroadcast;
import com.sidc.utils.net.UDPConnection;

/**
 * 
 * @author Allen Huang
 *
 */
public class Broadcast {
	
	public Broadcast(final int target, final RCUReceiverInfo receiver) throws SiDCException {
		UDPClientBroadcast broadcast = null;
		try {
			broadcast = new UDPClientBroadcast(new UDPConnection());
			broadcast.send(new Gson().toJson(receiver).getBytes(), target);
		} finally {
			broadcast.close();
		}
	}
}
