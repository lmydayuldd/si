/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;

public class ModeInitialDevicesBean implements Serializable {
	private static final long serialVersionUID = -2127724662862429523L;
	private String keycode;
	private int status;
	private boolean power;
	private int function;
	private int temperature;
	private int speed;
	private int timer;

	public String getKeycode() {
		return keycode;
	}

	public int getStatus() {
		return status;
	}

	public boolean isPower() {
		return power;
	}

	public int getFunction() {
		return function;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getSpeed() {
		return speed;
	}

	public int getTimer() {
		return timer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialDevicesBean [keycode=");
		builder.append(keycode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", power=");
		builder.append(power);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", timer=");
		builder.append(timer);
		builder.append("]");
		return builder.toString();
	}

	public ModeInitialDevicesBean(String keycode, int status, boolean power, int function, int temperature, int speed,
			int timer) {
		super();
		this.keycode = keycode;
		this.status = status;
		this.power = power;
		this.function = function;
		this.temperature = temperature;
		this.speed = speed;
		this.timer = timer;
	}

}
