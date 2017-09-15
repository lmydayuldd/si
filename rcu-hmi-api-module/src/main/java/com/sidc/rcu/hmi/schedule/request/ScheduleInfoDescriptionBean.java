package com.sidc.rcu.hmi.schedule.request;

import java.io.Serializable;

public class ScheduleInfoDescriptionBean implements Serializable {
	private static final long serialVersionUID = -4202596932964946848L;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleInfoDescriptionBean [ispower=");
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

	public ScheduleInfoDescriptionBean(boolean isPower, int function, int temperature, int timer) {
		super();
		this.isPower = isPower;
		this.function = function;
		this.temperature = temperature;
		this.timer = timer;
	}

}
