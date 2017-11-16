package com.sidc.zhongshan.connector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.connector.RcuConnector;
import com.sidc.rcu.connector.rmi.intf.RCUReciverRemote;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.SystemStatus;
import com.sidc.zhongshan.intf.RcuConfig;
import com.sidc.zhongshan.management.RCUReceiverHandlerThread;

/**
 * 
 * @author Allen Huang
 * @param <Serializable>
 *
 */
public class RCUReceiveConnector implements RcuConnector {

	private final static Logger logger = LoggerFactory.getLogger(RCUReceiveConnector.class);

	private int localPort;
	private DatagramSocket socket;
	private RCUReciverRemote stub;
	private int threadPoolSize;

	public RCUReceiveConnector(int localPort, RCUReciverRemote stub, int threadPoolSize) {
		this.localPort = localPort;
		this.stub = stub;
		this.threadPoolSize = threadPoolSize;
	}

	public boolean isConnection() throws SiDCException {
		// TODO Auto-generated method stub
		if (this.socket == null) {
			throw new SiDCException(SystemStatus.UDP_CONNECTION_FAIL, "Fail to build receive socket UDP");
		}
		return true;
	}

	public void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		this.socket = new DatagramSocket(this.localPort);
		this.socket.setReuseAddress(true);
		this.socket.setBroadcast(true);
	}

	public void send(RCUCommander command) throws SiDCException {
		// TODO Auto-generated method stub

	}

	public Object receive() throws SiDCException, SocketException, IOException, InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(this.threadPoolSize);

		while (!this.socket.isClosed()) {

			byte[] receivePacket = new byte[RcuConfig.BYTES];
			DatagramPacket packet = new DatagramPacket(receivePacket, receivePacket.length);

			try {
				this.socket.receive(packet);

				StringBuilder receiver = new StringBuilder();
				for (int i = 0; i < packet.getLength(); i++) {
					int hex = (int) packet.getData()[i] & 0xFF;
					receiver.append(Integer.toHexString(hex).toUpperCase().toString());
				}
				logger.debug(packet.getAddress().getHostAddress() + " = " + receiver.toString() + " receiver length : "
						+ packet.getLength());

				executor.execute(new RCUReceiverHandlerThread(packet, stub));

			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}

		}

		return null;
	}

	public void close() throws SiDCException {
		// TODO Auto-generated method stub
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
