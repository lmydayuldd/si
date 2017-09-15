package com.sidc.rcu.hmi.timer;

import java.net.InetSocketAddress;
import java.util.TimerTask;

import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.udp.connection.UDPConnection;
import com.sidc.rcu.hmi.udp.connection.UDPServer;
import com.sidc.rcu.hmi.udp.connection.UdpSetting;
import com.sidc.utils.common.DataCenter;

public class UDPServerTimer extends TimerTask {
	private UDPConnection udp;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			final Thread receiverThread = (Thread) DataCenter.getInstance().get(CommonDataKey.UDP_THEAD);

			if (!receiverThread.isAlive() || receiverThread.isInterrupted()) {
				udp = new UDPConnection(new InetSocketAddress(UdpSetting.UDP_REC));
				Thread udpThread = new UDPServer(udp);
				
				udpThread.start();
				
				DataCenter.getInstance().put(CommonDataKey.UDP_THEAD, udpThread);
			}
		} catch (Exception e) {
			if (udp == null)
				return;
			udp.close();
		}
	}

}
