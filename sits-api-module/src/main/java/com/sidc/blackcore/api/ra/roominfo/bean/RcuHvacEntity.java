package com.sidc.blackcore.api.ra.roominfo.bean;

public class RcuHvacEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2264315323021786378L;
	private int status;
	private int mode;
	private int temperature;
	private int actualTemp;

	public int getStatus() {
		return status;
	}

	public int getMode() {
		return mode;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getActualTemp() {
		return actualTemp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuHvacEntity [status=");
		builder.append(status);
		builder.append(", mode=");
		builder.append(mode);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", actualTemp=");
		builder.append(actualTemp);
		builder.append("]");
		return builder.toString();
	}

	public RcuHvacEntity(int status, int mode, int temperature, int actualTemp) {
		super();
		this.status = status;
		this.mode = mode;
		this.temperature = temperature;
		this.actualTemp = actualTemp;
	}

}
