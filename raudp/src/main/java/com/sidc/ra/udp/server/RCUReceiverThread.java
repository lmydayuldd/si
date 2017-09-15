/**
 * 
 */
package com.sidc.ra.udp.server;

import com.sidc.rcu.connector.connector.RcuConnector;
import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public class RCUReceiverThread extends Thread {

	private RcuConnector connector;

	/**
	 * 
	 */
	public RCUReceiverThread(RcuConnector connector) {
		this.connector = connector;
	}

	@Override
	public void run() {

		try {
			connector.init();
			connector.receive();
		} catch (SiDCException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public RcuConnector getConnector() {
		return connector;
	}
}
