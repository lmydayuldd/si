/**
 * 
 */
package com.sidc.rcu.hmi.listener;

import java.net.InetSocketAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.conf.SystemConfiguration;
import com.sidc.rcu.hmi.udp.connection.UDPConnection;
import com.sidc.rcu.hmi.udp.connection.UDPServer;
import com.sidc.rcu.hmi.udp.connection.UdpSetting;

public class UDPInitialListener implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(UDPInitialListener.class);
	private UDPConnection udp;

	public UDPInitialListener() {
		// TODO Auto-generated constructor stub
	}

	public void contextDestroyed(ServletContextEvent context) {
		// TODO Auto-generated method stub
		if (udp == null)
			return;
		udp.close();
	}

	public void contextInitialized(ServletContextEvent context) {
		logger.info("1/2 : Start to initial UDP server and websocket setting.");

		try {
			new SystemConfiguration().initial();
			logger.info("2/3 : initial websocket setting success.");

			udp = new UDPConnection(new InetSocketAddress(UdpSetting.UDP_REC));
			Thread udpThread = new UDPServer(udp);
			udpThread.start();

			DataCenter.getInstance().put(CommonDataKey.UDP_THEAD, udpThread);
			
			logger.info("3/3 : Start UDP server success.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			if (udp == null)
				return;
			udp.close();

		}
	}
}
