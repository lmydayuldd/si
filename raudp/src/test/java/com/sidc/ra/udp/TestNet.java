package com.sidc.ra.udp;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.net.NetUtils;
import com.sidc.utils.status.SystemStatus;

public class TestNet {

	@Test
	public void test() throws SocketException {
		long start = System.currentTimeMillis();

//		List<InetAddress> list = NetUtils.listBroadcast();
//
//		for (InetAddress ia : list) {
//			System.out.println(ia.getHostAddress());
//		}

		try {
			InetAddress address = findBroadcastTarget();
			System.out.println(address.getHostAddress());
		} catch (SiDCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(System.currentTimeMillis() - start);
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
			System.out.println(ia.getHostAddress());
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
}
