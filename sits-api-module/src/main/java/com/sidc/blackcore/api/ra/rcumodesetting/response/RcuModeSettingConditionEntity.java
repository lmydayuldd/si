package com.sidc.blackcore.api.ra.rcumodesetting.response;

public class RcuModeSettingConditionEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -756502176705324849L;
	private boolean isPower;
	private String function;
	private String temperature;
	private String speed;
	private String timer;
	private String address;
	private String status;

	public boolean isPower() {
		return isPower;
	}

	public String getFunction() {
		return function;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getSpeed() {
		return speed;
	}

	public String getTimer() {
		return timer;
	}

	public String getAddress() {
		return address;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeSettingConditionEntity [isPower=");
		builder.append(isPower);
		builder.append(", function=");
		builder.append(function);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", timer=");
		builder.append(timer);
		builder.append(", address=");
		builder.append(address);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeSettingConditionEntity(boolean isPower, String function, String temperature, String speed,
			String timer, String address, String status) {
		super();
		this.isPower = isPower;
		this.function = function;
		this.temperature = temperature;
		this.speed = speed;
		this.timer = timer;
		this.address = address;
		this.status = status;
	}

	public RcuModeSettingConditionEntity(String function, String temperature, String speed, String timer,
			String address, String status) {
		super();
		this.function = function;
		this.temperature = temperature;
		this.speed = speed;
		this.timer = timer;
		this.address = address;
		this.status = status;
	}

}
