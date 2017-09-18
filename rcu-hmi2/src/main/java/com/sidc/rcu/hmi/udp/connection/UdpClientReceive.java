package com.sidc.rcu.hmi.udp.connection;

/**
 * 
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

import org.apache.commons.codec.CharEncoding;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.time.JsonDateDeserializer;
import com.sidc.utils.time.TimeStandardSetting;

public class UdpClientReceive {

	private DatagramSocket udp;
	private final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer())
			.setDateFormat(TimeStandardSetting.STANDARD_TIMESTAMP).create();

	public UdpClientReceive(DatagramSocket udp) {
		this.udp = udp;
	}

	public void execute() throws SiDCException {

		byte[] buff = new byte[UdpSetting.UDP_BUFF];

		while (true) {
			try {
				DatagramPacket receivePacket = new DatagramPacket(buff, buff.length);
				udp.receive(receivePacket);

				buff = receivePacket.getData();
				String s = new String(buff, CharEncoding.UTF_8);
				// System.out.println(
				// receivePacket.getAddress().getHostAddress() + " : " +
				// receivePacket.getPort() + " - " + s +
				// System.currentTimeMillis());

//				UdpClientRecevieBean info = gson.fromJson(gson.toJson(s), UdpClientRecevieBean.class);
//				dataProcess(info,s);

			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		this.udp.close();
	}

//	@SuppressWarnings("unchecked")
//	private void dataProcess(final UdpClientRecevieBean entity, final String json) {
//		if (entity.getClassification().equals(CommonDataKey.RCU_BULB)) {
//			BulbReceiverBean bulbEntity = gson.fromJson(json, BulbReceiverBean.class);
//
//			HashMap<String, BulbReceiverBean> bulbHashMap = (HashMap<String, BulbReceiverBean>) DataCenter.getInstance()
//					.get(CommonDataKey.RCU_BULB);
//
//			bulbHashMap.put(bulbEntity.getRoomNo(), bulbEntity);
//		} else if (entity.getClassification().equals(CommonDataKey.RCU_BULB)) {
//			BulbReceiverBean bulbEntity = gson.fromJson(json, BulbReceiverBean.class);
//
//			HashMap<String, BulbReceiverBean> bulbHashMap = (HashMap<String, BulbReceiverBean>) DataCenter.getInstance()
//					.get(CommonDataKey.RCU_BULB);
//
//			bulbHashMap.put(bulbEntity.getRoomNo(), bulbEntity);
//		}
//	}
}
