package com.sidc.zhongshan.intf;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * 
 * @author Allen Huang
 *
 */
public class Utils {

	private Utils() {
	}

	private static class LazyHolder {
		public static final Utils INSTANCE = new Utils();
	}

	public static Utils getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public String[] byteToHex(byte[] buff) {

		int length = buff.length;
		String[] builder = new String[length];

		for (int i = 0; i < length; i++) {
			int v = buff[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				builder[i] = "0x0" + hv.toUpperCase();
			} else {
				builder[i] = "0x" + hv.toUpperCase();
			}
		}

		return builder;
	}

	public byte[] hexToBytes(String hexString) {

		char[] hex = hexString.toCharArray();
		// 轉rawData長度減半
		int length = hex.length / 4;
		byte[] rawData = new byte[length];
		for (int i = 0; i < length; i++) {
			// 先將hex資料轉10進位數值
			int high = Character.digit(hex[i * 4 + 2], 16);
			int low = Character.digit(hex[i * 4 + 3], 16);
			// 將第一個值的二進位值左平移4位,ex: 00001000 => 10000000 (8=>128)
			// 然後與第二個值的二進位值作聯集ex: 10000000 | 00001100 => 10001100 (137)
			int value = (high << 4) | low;
			// 與FFFFFFFF作補集
			if (value > 127)
				value -= 256;

			// 最後轉回byte就OK
			rawData[i] = (byte) value;
		}

		return rawData;
	}

	public String makesUpZero(int str, int lenSize) {
		return makesUpZero(String.valueOf(str), lenSize);
	}

	public String makesUpZero(String str, int lenSize) {

		String zero = "0000000000";
		String returnValue = zero;
		returnValue = zero + str;

		return returnValue.substring(returnValue.length() - lenSize);

	}

	public String fillData(ArrayList<String> data) {

		String sData = "";
		for (int i = 0; i < 40; i++) {
			if (i > data.size() - 1) {
				sData += "0x00";
			} else {
				sData += data.get(i);
			}
		}

		return sData;
	}

	public String sumHex(String hexString) {

		String sumhex = "";
		int length = hexString.length() / 4;
		for (int i = 0; i < length; i++) {
			String hex = hexString.substring(i * 4, (i + 1) * 4);
			if (sumhex.equals("")) {
				sumhex = hex;
				continue;
			}
			sumhex = "0x" + Integer.toHexString(Integer.decode(sumhex) ^ Integer.decode(hex)).toUpperCase();
		}

		return sumhex;
	}

	public String port(final InetAddress IP) {

		byte[] ip = IP.getAddress();

		int portnumber = 10000;

		int floor = (reduce((int) ip[2] & 0xFF)) * 256;

		int room = (int) ip[3] & 0xFF;

		portnumber += floor + room;

		return String.valueOf(portnumber);
	}

	private int reduce(int args) {

		if (args >= 200)
			args -= 200;
		if (args >= 100)
			args -= 100;

		return args;
	}

	public byte[] getBytes() {
		byte[] bytes = new byte[40];
		for (int i = 0; i < 40; i++) {
			int preset = 0xFF;
			bytes[i] = (byte) preset;
		}

		return bytes;
	}

	public String stringData(byte[] bytes) {
		String Data = "";
		String[] byteArray = byteToHex(bytes);
		for (String string : byteArray) {
			Data += string;
		}

		return Data;
	}

	public InetAddress roomNoToIP(String roomno) throws UnknownHostException {

		String areaA = roomno.substring(0, roomno.length() - 2);
		String areaB = roomno.substring(2, roomno.length());
		if (areaB.equals("01")) areaB = "101";

		return InetAddress.getByName("192.168." + areaA + "." +areaB);
	}

	public String ipToRoomNo(InetAddress ip) {

		String floor = Integer.toHexString(Integer.parseInt(String.valueOf(ip.getAddress()[2]), 16));
		String roomNo = Integer.toHexString(Integer.parseInt(String.valueOf(ip.getAddress()[3]), 16));
		roomNo = roomNo.equals("101") ? "1" : roomNo;
		roomNo = floor + makesUpZero(roomNo, 2);

		return roomNo;
	}
}
