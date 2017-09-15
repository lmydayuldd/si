package com.sidc.zhongshan.connector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.connector.RcuConnector;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.RcuSenderLog;
import com.sidc.utils.status.SystemStatus;
import com.sidc.zhongshan.intf.Utils;
import com.sidc.zhongshan.management.Command;

/**
 * 
 * @author Allen Huang
 *
 */
public class RCUSendConnector implements RcuConnector {

	private final static Logger logger = LoggerFactory.getLogger(RCUSendConnector.class);
	private String roomNo;
	private int rcuPort;
	private DatagramSocket socket;
	private InetAddress serverIP;
	private final static int SEND_TIMEOUT = 5000;

	public RCUSendConnector(String roomNo, int rcuPort) {
		this.roomNo = roomNo;
		this.rcuPort = rcuPort;
	}

	public boolean isConnection() throws SiDCException {
		// TODO Auto-generated method stub
		if (this.socket == null) {
			throw new SiDCException(SystemStatus.UDP_CONNECTION_FAIL, "Fail to build send socket UDP");
		}
		return !socket.isClosed();
	}

	public void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		this.serverIP = Utils.getInstance().roomNoToIP(Utils.getInstance().makesUpZero(this.roomNo, 4));

		this.socket = new DatagramSocket();
		this.socket.setReuseAddress(true);
		this.socket.setSoTimeout(SEND_TIMEOUT);
	}

	public void send(RCUCommander command) throws SiDCException {
		// TODO Auto-generated method stub

		RcuSenderLog.getInstance().initial(logger, command.getUuid());
		RcuSenderLog.getInstance().setRoomNo(command.getRoomno());
		RcuSenderLog.getInstance().setKeycode(command.getKeycode());

		try {
			RcuSenderLog.getInstance().debug(command.toString());
			String packetMessage = Command.rcuCommon(this.serverIP, command);
			RcuSenderLog.getInstance().debug(packetMessage);

			byte[] sendPacket = Utils.getInstance().hexToBytes(packetMessage);
			InetSocketAddress inetSocketAddress = new InetSocketAddress(this.serverIP, this.rcuPort);
			this.socket.send(new DatagramPacket(sendPacket, sendPacket.length, inetSocketAddress));
		} catch (SocketException e) {
			// TODO: handle exception
			RcuSenderLog.getInstance().error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO: handle exception
			RcuSenderLog.getInstance().error(e.getMessage(), e);
		} finally {
			RcuSenderLog.getInstance().writeRecord();
			close();
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
