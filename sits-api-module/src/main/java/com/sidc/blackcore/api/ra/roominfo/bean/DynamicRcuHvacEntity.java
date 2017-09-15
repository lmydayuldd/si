package com.sidc.blackcore.api.ra.roominfo.bean;

public class DynamicRcuHvacEntity implements java.io.Serializable {
	private static final long serialVersionUID = -650977190810403024L;
	private String keycode;
	private int pipe;
	private int power;
	private int roomtemperature;
	private int temperature;

	public String getKeycode() {
		return keycode;
	}

	public int getPipe() {
		return pipe;
	}

	public int getPower() {
		return power;
	}

	public int getRoomtemperature() {
		return roomtemperature;
	}

	public int getTemperature() {
		return temperature;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DynamicRcuHvacEntity [keycode=");
		builder.append(keycode);
		builder.append(", pipe=");
		builder.append(pipe);
		builder.append(", power=");
		builder.append(power);
		builder.append(", roomtemperature=");
		builder.append(roomtemperature);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append("]");
		return builder.toString();
	}

	public DynamicRcuHvacEntity(String keycode, int pipe, int power, int roomtemperature, int temperature) {
		super();
		this.keycode = keycode;
		this.pipe = pipe;
		this.power = power;
		this.roomtemperature = roomtemperature;
		this.temperature = temperature;
	}

}
