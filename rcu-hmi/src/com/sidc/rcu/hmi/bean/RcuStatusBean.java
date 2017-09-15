package com.sidc.rcu.hmi.bean;

import java.io.Serializable;

public class RcuStatusBean implements Serializable {
	private static final long serialVersionUID = 3431176461600851762L;
	private String status;
	private String keycode;
	private String mode;
	private String temperature;
	private String speed;
	private String actualTemp;
	private String isAirConditioning;
	private String position;
	private String authorization;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeycode() {
		return keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getActualTemp() {
		return actualTemp;
	}

	public void setActualTemp(String actualTemp) {
		this.actualTemp = actualTemp;
	}

	public String getIsAirConditioning() {
		return isAirConditioning;
	}

	public void setIsAirConditioning(String isAirConditioning) {
		this.isAirConditioning = isAirConditioning;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public RcuStatusBean(String status, String keycode, String mode, String temperature, String speed,
			String actualTemp, String isAirConditioning, String position, String authorization) {
		super();
		this.status = status;
		this.keycode = keycode;
		this.mode = mode;
		this.temperature = temperature;
		this.speed = speed;
		this.actualTemp = actualTemp;
		this.isAirConditioning = isAirConditioning;
		this.position = position;
		this.authorization = authorization;
	}

	public RcuStatusBean(String status, String keycode) {
		super();
		this.status = status;
		this.keycode = keycode;
	}
}
