/**
 * 
 */
package com.sidc.zhongshan.management;

import java.net.DatagramPacket;

import com.sidc.rcu.connector.rmi.intf.RCUReciverRemote;

/**
 * @author Nandin
 *
 */
public class RCUReceiverHandlerThread implements Runnable {

	private DatagramPacket packet;
	private RCUReciverRemote stub;

	/**
	 * 
	 */
	public RCUReceiverHandlerThread(DatagramPacket packet, RCUReciverRemote stub) {
		this.packet = packet;
		this.stub = stub;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		new RCUReceiverNotice(this.packet, this.stub).process();
	}

}
