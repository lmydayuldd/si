package com.sidc.rcu.hmi.rcucommand.request;

import java.io.Serializable;

public class HvacCenterControlRequest implements Serializable {
	private static final long serialVersionUID = 7071373393333728615L;
	private int function;
	private int temperature = 25;

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
