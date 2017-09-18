package com.sidc.rcu.hmi.modesetting.bean;

import java.io.Serializable;

public class RcuModeCondition implements Serializable {
	private static final long serialVersionUID = -273915813319958375L;

	private String status;
	private String temperature;
	private String function;
	private boolean power;
	private String speed;
	private String timer;

	public String getStatus() {
		return status;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getFunction() {
		return function;
	}

	public boolean isPower() {
		return power;
	}

	public String getSpeed() {
		return speed;
	}

	public String getTimer() {
		return timer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeCondition [status=");
		builder.append(status);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", function=");
		builder.append(function);
		builder.append(", power=");
		builder.append(power);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", timer=");
		builder.append(timer);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeCondition(String status, String temperature, String function, boolean power, String speed,
			String timer) {
		super();
		this.status = status;
		this.temperature = temperature;
		this.function = function;
		this.power = power;
		this.speed = speed;
		this.timer = timer;
	}

}
