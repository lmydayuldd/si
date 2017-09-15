package com.sidc.blackcore.api.ra.rcumodesetting.bean;

public class RcuDeviceHvacSettingBean implements java.io.Serializable {
	private static final long serialVersionUID = -4007549875936114607L;
	private boolean power;
	private int function;
	private int temperature;
	private int time;
	private int speed;
	private final int address = 8;

	public boolean isPower() {
		return power;
	}

	public int getFunction() {
		return function;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getTime() {
		return time;
	}

	public int getSpeed() {
		return speed;
	}

	public int getAddress() {
		return address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceHvacSettingBean [power=");
		builder.append(power);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", time=");
		builder.append(time);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

	public RcuDeviceHvacSettingBean(boolean power, int function, int temperature, int time, int speed) {
		super();
		this.power = power;
		this.function = function;
		this.temperature = temperature;
		this.time = time;
		this.speed = speed;
	}

}
