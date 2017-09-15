package com.zhongshan.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.junit.Test;

import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.rcu.connector.bean.command.AirConditionCommander;
import com.sidc.rcu.connector.bean.command.BulbCommander;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.bean.command.ServiceCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.log.RcuSenderLog;
import com.sidc.utils.net.NetUtils;
import com.sidc.zhongshan.connector.RCUInitialConnector;
import com.sidc.zhongshan.connector.RCUReceiveConnector;
import com.sidc.zhongshan.connector.RCUSendConnector;
import com.sidc.zhongshan.intf.Utils;
import com.sidc.zhongshan.management.Command;

/**
 * 
 * @author Allen Huang
 *
 */
public class TestNet {

	@Test
	public void test() throws SocketException {
		long start = System.currentTimeMillis();

		List<InetAddress> list = NetUtils.listBroadcast();

		for (InetAddress ia : list) {
			System.out.println(ia.getHostAddress());
		}

		System.out.print(System.currentTimeMillis() - start);
	}

	@Test
	public void testRcuConnector() {

		String roomno = "1102";
		InetAddress ip = null;
		try {
			ip = Utils.getInstance().roomNoToIP(Utils.getInstance().makesUpZero(roomno, 4));
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
			socket.setReuseAddress(true);
			socket.setSoTimeout(3000);

			final int mark = (int) ip.getAddress()[3] & 0xFF;
			
			String packetHeader = "0x850x86";
			String packetType = "0x00";
			String roomIpAddr = "0x" + Utils.getInstance().makesUpZero(Integer.toHexString(mark), 2).toUpperCase();
			String dataLen = "0x31";
			
			String data = "0xC10x010x010x1A0x030x000x080x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x000x00";

			SimpleDateFormat sdfor = new SimpleDateFormat("HHmmss");
			String now = sdfor.format(new Date());
			String hour = now.substring(0, 2);
			String minute = now.substring(2, 4);
			String second = now.substring(4, 6);
			
			String dateTime = "0x" + Utils.getInstance().makesUpZero(Integer.toHexString(Integer.parseInt(hour)), 2).toUpperCase() + "0x"
					+ Utils.getInstance().makesUpZero(Integer.toHexString(Integer.parseInt(minute)), 2).toUpperCase() + "0x"
					+ Utils.getInstance().makesUpZero(Integer.toHexString(Integer.parseInt(second)), 2).toUpperCase();

			String sumHex = Utils.getInstance().sumHex(packetType + roomIpAddr + dataLen + data + dateTime);

			String packetMessage = packetHeader + packetType + roomIpAddr + dataLen + data + dateTime + sumHex;
			
			byte[] sendPacket = Utils.getInstance().hexToBytes(packetMessage);
			InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, 8012);
			socket.send(new DatagramPacket(sendPacket, sendPacket.length, inetSocketAddress));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.disconnect();
			socket.close();
		}

	}
}
