package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.abs.AbstractRcuBean;

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

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public void setActualTemp(String actualTemp) {
		this.actualTemp = actualTemp;
	}

	public void setIsAirConditioning(String isAirConditioning) {
		this.isAirConditioning = isAirConditioning;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setLangs(List<LanguageBean> langs) {
		this.langs = langs;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public RcuHvacInfoBean(String roomNo, String keycode, List<LanguageBean> langs) {
		this.roomNo = roomNo;
		this.keycode = keycode;
		this.langs = langs;
		this.time = new Date();
	}

}
