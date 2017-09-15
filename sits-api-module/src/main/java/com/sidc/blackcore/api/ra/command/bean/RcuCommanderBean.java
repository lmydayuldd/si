package com.sidc.blackcore.api.ra.command.bean;

import java.io.Serializable;

public class RcuCommanderBean implements Serializable {
	private static final long serialVersionUID = -5723111529295985829L;
	private boolean power;
	private String function;
	private String temperature;
	private String value;

	public boolean isPower() {
		return power;
	}

	public String getFunction() {
		return function;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("testBean [power=");
		builder.append(power);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	public RcuCommanderBean(boolean power, String function, String temperature, String value) {
		super();
		this.power = power;
		this.function = function;
		this.temperature = temperature;
		this.value = value;
	}

}
