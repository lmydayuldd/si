package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.abs.AbstractRcuBean;

public class RcuCardInfoBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = -5754299315043344015L;
	private String roomNo;
	private String status;
	private String authorization;
	private String keycode;
	private List<LanguageBean> langs;
	private Date time;

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getKeycode() {
		return keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public List<LanguageBean> getLangs() {
		return langs;
	}

	public void setLangs(List<LanguageBean> langs) {
		this.langs = langs;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public RcuCardInfoBean(String roomNo, String status, String authorization, String keycode, List<LanguageBean> langs,
			Date time) {
		super();
		this.roomNo = roomNo;
		this.status = status;
		this.authorization = authorization;
		this.keycode = keycode;
		this.langs = langs;
		this.time = time;
	}

	public RcuCardInfoBean(String roomNo, String keycode, List<LanguageBean> langs) {
		super();
		this.roomNo = roomNo;
		this.keycode = keycode;
		this.langs = langs;
		this.time = new Date();
	}
}
