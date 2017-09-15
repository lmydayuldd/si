package com.sidc.zhongshan.bean;

import com.sidc.zhongshan.intf.Utils;

/**
 * 
 * @author Allen Huang
 *
 */
public class HVACHex {

	public static byte[] command(byte[] bytes, final Boolean isPower, final Integer function, final Integer temperature,
			final Integer speed, final Integer timer, final Byte address) {

		byte[] getbyte = new byte[1];
		if (isPower != null) {
			String powerValue = isPower ? Integer.toHexString(1) : Integer.toHexString(0);
			getbyte = Utils.getInstance().hexToBytes("0x" + Utils.getInstance().makesUpZero(powerValue, 2));
			bytes[0] = getbyte[0];
		}

		if (function != null) {
			getbyte = new byte[1];
			String functionValue = Integer.toHexString(function);
			getbyte = Utils.getInstance().hexToBytes("0x" + Utils.getInstance().makesUpZero(functionValue, 2));
			bytes[1] = getbyte[0];
		}

		if (temperature != null) {
			getbyte = new byte[1];
			String temperatureValue = Integer.toHexString(temperature);
			getbyte = Utils.getInstance().hexToBytes("0x" + Utils.getInstance().makesUpZero(temperatureValue, 2));
			bytes[2] = getbyte[0];
		}

		if (speed != null) {
			getbyte = new byte[1];
			String speedValue = Integer.toHexString(speed);
			getbyte = Utils.getInstance().hexToBytes("0x" + Utils.getInstance().makesUpZero(speedValue, 2));
			bytes[3] = getbyte[0];
		}

		if (timer != null) {
			getbyte = new byte[1];
			String timerValue = Integer.toHexString(timer);
			getbyte = Utils.getInstance().hexToBytes("0x" + Utils.getInstance().makesUpZero(timerValue, 2));
			bytes[4] = getbyte[0];
		}

		getbyte = new byte[1];
		getbyte = Utils.getInstance().hexToBytes("0x" + Utils.getInstance().getInstance().makesUpZero(address, 2));
		bytes[5] = getbyte[0];

		return bytes;
	}
}
