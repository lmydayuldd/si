package com.sidc.rcu.hmi.bean.receiver;

import java.io.Serializable;

public class RcuServiceBean implements Serializable {

	private static final long serialVersionUID = 7879828085435153628L;

	private String status;
	private String mode;
	private String temperature;
	private String speed;
	private String actualTemp;
	private String isAirConditioning;
	private String position;
	private String keycode;
	private String cardType;
	private String authorization;

	public String getStatus() {
		return status;
	}

	public String getMode() {
		return mode;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getSpeed() {
		return speed;
	}

	public String getActualTemp() {
		return actualTemp;
	}

	public String getIsAirConditioning() {
		return isAirConditioning;
	}

	public String getPosition() {
		return position;
	}

	public String getKeycode() {
		return keycode;
	}

	public String getCardType() {
		return cardType;
	}

	public String getAuthorization() {
		return authorization;
	}

	public RcuServiceBean(String status, String mode, String temperature, String speed, String actualTemp,
			String isAirConditioning, String position, String keycode, String cardType, String authorization) {
		super();
		this.status = status;
		this.mode = mode;
		this.temperature = temperature;
		this.speed = speed;
		this.actualTemp = actualTemp;
		this.isAirConditioning = isAirConditioning;
		this.position = position;
		this.keycode = keycode;
		this.cardType = cardType;
		this.authorization = authorization;
	}

}
