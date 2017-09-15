package com.sidc.zhongshan.connector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.connector.RcuConnector;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.SystemStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class RCUInitialConnector implements RcuConnector {

	private int rcuPort;
	private DatagramSocket socket;
	
	public RCUInitialConnector(int rcuPort) {
		this.rcuPort = rcuPort;
	}

	public boolean isConnection() throws SiDCException {
		// TODO Auto-generated method stub
		if (this.socket == null) {
			throw new SiDCException(SystemStatus.UDP_CONNECTION_FAIL, "Fail to build initial socket UDP");
		}
		return true;
	}

	public void init() throws SocketException, IOException {
			this.socket = new DatagramSocket();
			this.socket.setReuseAddress(true);
	}

	public void send(RCUCommander command) throws SiDCException {
		// TODO Auto-generated method stub

		InetSocketAddress broadCast = new InetSocketAddress("255.255.255.255", this.rcuPort);
		byte[] array = new byte[] { (byte) 133, (byte) 134, 0, (byte) 255, 49, (byte) 255, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				(byte) 144, (byte) 131, 104, (byte) 129 };
		try {
			this.socket.send(new DatagramPacket(array, array.length, broadCast));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object receive() throws SiDCException, SocketException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void close() throws SiDCException {

		if (this.socket != null) {
			try {
				this.socket.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				this.socket.disconnect();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public boolean testConnection() {
		// TODO Auto-generated method stub
		return false;
	}
}
