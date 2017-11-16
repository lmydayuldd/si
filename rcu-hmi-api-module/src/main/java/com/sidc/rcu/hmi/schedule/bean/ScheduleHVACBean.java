package com.sidc.rcu.hmi.schedule.bean;

import java.io.Serializable;

public class ScheduleHVACBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6827657266663247551L;
	private boolean isPower;
	private int function;
	private int temperature;
	private int timer;

	public boolean isPower() {
		return isPower;
	}

	public int getFunction() {
		return function;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public ScheduleHVACBean(boolean isPower, int function, int temperature, int timer) {
		super();
		this.isPower = isPower;
		this.function = function;
		this.temperature = temperature;
		this.timer = timer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleHVACBean [isPower=");
		builder.append(isPower);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", timer=");
		builder.append(timer);
		builder.append("]");
		return builder.toString();
	}
}
