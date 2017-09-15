package com.sidc.zhongshan.management;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class Command {

	public static String rcuCommon(final InetAddress serverIP, final RCUCommander rcuCommand) throws SiDCException {

		final int mark = (int) serverIP.getAddress()[3] & 0xFF;

		String packetHeader = "0x850x86";
		String packetType = "0x00";
		String roomIpAddr = "0x" + Utils.getInstance().makesUpZero(Integer.toHexString(mark), 2).toUpperCase();
		String dataLen = "0x31";
		String data = new ZhongshanCommand(rcuCommand).handle();

		SimpleDateFormat sdfor = new SimpleDateFormat("HHmmss");
		String now = sdfor.format(new Date());
		String hour = now.substring(0, 2);
		String minute = now.substring(2, 4);
		String second = now.substring(4, 6);

		String dateTime = "0x"
				+ Utils.getInstance().makesUpZero(Integer.toHexString(Integer.parseInt(hour)), 2).toUpperCase() + "0x"
				+ Utils.getInstance().makesUpZero(Integer.toHexString(Integer.parseInt(minute)), 2).toUpperCase() + "0x"
				+ Utils.getInstance().makesUpZero(Integer.toHexString(Integer.parseInt(second)), 2).toUpperCase();

		String sumHex = Utils.getInstance().sumHex(packetType + roomIpAddr + dataLen + data + dateTime);

		String packetMessage = packetHeader + packetType + roomIpAddr + dataLen + data + dateTime + sumHex;

		return packetMessage;
	}
}
