package com.sidc.zhongshan.bean;

import java.math.BigInteger;

import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class BulbHex {

	public static byte[] Command(byte[] bytes, final int position, final int value) {

		byte[] getbyte = new byte[1];
		getbyte = Utils.getInstance()
				.hexToBytes("0x" + Utils.getInstance().makesUpZero(Integer.toHexString(value).toUpperCase(), 2));
		bytes[position] = getbyte[0];

		return bytes;
	}

	public static byte[] Command(byte[] bytes, final int position, final int circuit, final int value) {

		byte[] getbyte = new byte[1];
		getbyte[0] = bytes[position];
		String bit = binary(getbyte, 2);
		String[] bitsplit = new String[4];

		for (int i = 0; i < 4; i++) {
			int beginindex = i * 2;
			int endindex = beginindex + 2;
			bitsplit[i] = bit.substring(beginindex, endindex);
		}

		bitsplit[circuit] = Utils.getInstance().makesUpZero(String.valueOf(value), 2);

		String bitString = "";
		String byteString = "";
		for (int i = 0; i < bitsplit.length; i++) {
			bitString += bitsplit[i];
			if (i > 0) {
				if (bitsplit.length % i == 1) {
					byteString += "0x" + Integer.toHexString(Integer.parseInt(bitString, 2)).toUpperCase();
				}
			}
		}

		getbyte = Utils.getInstance().hexToBytes(byteString);

		bytes[position] = getbyte[0];

		return bytes;
	}

	private static String binary(final byte[] bytes, int radix) {
		// 這裡的1代表正數
		return new BigInteger(1, bytes).toString(radix);
	}
}
