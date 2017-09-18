package com.sidc.rcu.hmi.udp.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

import org.apache.commons.codec.CharEncoding;

import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.udp.inft.UDPProtocolIntf;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;
import com.sidc.utils.status.SystemStatus;

public class UdpClientSend implements UDPProtocolIntf {

	private DatagramSocket udp;
	private SocketAddress targetHost;
	private byte[] command;

	private final static int SEND_TIMEOUT = 3000;
	private final static int REC_TIMEOUT = 3000;

	public UdpClientSend(DatagramSocket udp, SocketAddress targetHost, byte[] command) {
		this.targetHost = targetHost;
		this.command = command;
		this.udp = udp;
	}

	public void execute() throws SiDCException {
		try {
			// idle
			// connection pool
			udp.setSoTimeout(SEND_TIMEOUT);
			udp.send(new DatagramPacket(command, command.length, targetHost));

			byte[] buff = new byte[UdpSetting.UDP_BUFF];
			udp.setSoTimeout(REC_TIMEOUT);
			DatagramPacket receivePacket = new DatagramPacket(buff, buff.length);
			udp.receive(receivePacket);

			buff = receivePacket.getData();
			String s = new String(buff, CharEncoding.UTF_8);
			
			
			
			DataCenter.getInstance().put("", "");
			
		} catch (SocketException e) {
			throw new SiDCException(SystemStatus.UDP_CONNECTION_FAIL, e.getMessage(), e);
		} catch (IOException e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
		}
	}

	public void close() {
		// TODO Auto-generated method stub
		this.udp.close();
	}

}
