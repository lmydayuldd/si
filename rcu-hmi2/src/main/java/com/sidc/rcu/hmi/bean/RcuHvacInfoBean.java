package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.framework.abs.AbstractRcuBean;

public class RcuHvacInfoBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = -4390908741362891477L;

	private String roomNo;
	private String keycode;
	private String status;
	private String mode;
	private String temperature;
	private String speed;
	private String actualTemp;
	private String isAirConditioning;
	private String position;
	private List<LanguageBean> langs;
	private Date time;

	public String getRoomNo() {
		return roomNo;
	}

	public String getKeycode() {
		return keycode;
	}

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

	public List<LanguageBean> getLangs() {
		return langs;
	}

	public Date getTime() {
		return time;
	}

	public RcuHvacInfoBean(String roomNo, String keycode, String status, String mode, String temperature, String speed,
			String actualTemp, String isAirConditioning, String position, List<LanguageBean> langs) {
		super();
		this.roomNo = roomNo;
		this.keycode = keycode;
		this.status = status;
		this.mode = mode;
		this.temperature = temperature;
		this.speed = speed;
		this.actualTemp = actualTemp;
		this.isAirConditioning = isAirConditioning;
		this.position = position;
		this.langs = langs;
		this.time = new Date();
	}

}
