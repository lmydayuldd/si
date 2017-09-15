/**
 * 
 */
package com.sidc.utils.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;
import com.sidc.utils.status.SystemStatus;

/**
 * @author Nandin
 *
 */
public class UDPClientBroadcast {

	private UDPConnection udp;
	private final static int SEND_TIMEOUT = 3000;

	public UDPClientBroadcast(UDPConnection udp) {
		this.udp = udp;
		try {
			udp.initial();
		} catch (SiDCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(final byte[] command, final int port) throws SiDCException {

		try {
			udp.getSocket().setSoTimeout(SEND_TIMEOUT);
			udp.getSocket().send(new DatagramPacket(command, command.length, findBroadcastTarget(), port));
		} catch (SocketException e) {
			throw new SiDCException(SystemStatus.UDP_CONNECTION_FAIL, e.getMessage(), e);
		} catch (IOException e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
		}
	}

	private InetAddress findBroadcastTarget() throws SiDCException {
		InetAddress targetHost = null;

		List<InetAddress> list = null;
		try {
			list = NetUtils.listBroadcast();
		} catch (SocketException e) {
			throw new SiDCException(SystemStatus.UDP_CONNECTION_FAIL, e.getMessage(), e);
		}

		if (list == null || list.isEmpty()) {
			throw new SiDCException(SystemStatus.UDP_BROADCAST_FAIL, "Fail to load Broadcast Address.");
		}

		for (InetAddress ia : list) {
			if (ia.getHostAddress().indexOf("10.60.255.255") > -1) {
				targetHost = ia;
				break;
			} else if (ia.getHostAddress().indexOf("10.255.255.255") > -1) {
				targetHost = ia;
				break;
			}

		}
		return targetHost;
	}

	public void close() {
		try {
			udp.close();
		} catch (Exception ex) {
			// do nothing
		}

	}

}
