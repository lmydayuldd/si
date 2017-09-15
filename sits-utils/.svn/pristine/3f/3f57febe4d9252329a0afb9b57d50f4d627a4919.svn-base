/**
 * 
 */
package com.sidc.utils.net;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Nandin
 *
 */
public class NetUtils {

	public static List<InetAddress> listBroadcast() throws SocketException {

		List<InetAddress> list = new ArrayList<InetAddress>();

		Enumeration<NetworkInterface> nicList = NetworkInterface.getNetworkInterfaces();
		while (nicList.hasMoreElements()) {
			NetworkInterface nic = nicList.nextElement();
			if (nic.isUp() && !nic.isLoopback()) {
				for (InterfaceAddress ia : nic.getInterfaceAddresses()) {
					if (ia.getBroadcast() == null) {
						continue;
					}

					list.add(ia.getBroadcast());
				}
			}
		}

		return list;
	}

	public static List<String> listIPAddress() throws SocketException {
		List<String> list = new ArrayList<String>();

		Enumeration e = NetworkInterface.getNetworkInterfaces();

		while (e.hasMoreElements()) {
			NetworkInterface n = (NetworkInterface) e.nextElement();
			Enumeration ee = n.getInetAddresses();
			while (ee.hasMoreElements()) {
				InetAddress i = (InetAddress) ee.nextElement();
				list.add(i.getHostAddress());
			}
		}

		return list;
	}

	public static String findLocalAllowIP(String filter) throws SocketException {
		String result = null;
		List<String> ipList = NetUtils.listIPAddress();

		for (String ip : ipList) {

			if (ip.contains(filter)) {
				result = ip;
				break;
			}
		}

		return result;
	}
}
