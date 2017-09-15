package com.sidc.blackcore.api.ra.rcuhvaccentercontrol.request;

import java.io.Serializable;

public class HvacCenterControlRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4761060672985777087L;
	private int function;
	private int temperature;

	public int getFunction() {
		return function;
	}

	public int getTemperature() {
		return temperature;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HvacCenterControlRequest [function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append("]");
		return builder.toString();
	}

	public HvacCenterControlRequest(int function, int temperature) {
		super();
		this.function = function;
		this.temperature = temperature;
	}

}
