package com.sidc.rcu.hmi.udp.connection;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.SystemStatus;

public class UDPConnection {
	private DatagramSocket socket;
	private InetSocketAddress targetHost;

	public UDPConnection() {
	}

	public UDPConnection(InetSocketAddress targetHost) {
		super();
		this.targetHost = targetHost;
	}

	public void initial() throws SiDCException {

		try {
			socket = new DatagramSocket(targetHost);
			socket.setReuseAddress(true);
		} catch (SocketException e) {
			throw new SiDCException(SystemStatus.UDP_CONNECTION_FAIL, "Fail to build UDP", e);
		}
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void close() {
		if (socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} finally {
				socket.disconnect();
			}
		}
	}
}
