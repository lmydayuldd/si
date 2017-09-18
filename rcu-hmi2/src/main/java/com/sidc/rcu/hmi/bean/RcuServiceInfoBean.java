package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.framework.abs.AbstractRcuBean;

public class RcuServiceInfoBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = -4297064446617757854L;
	private String roomNo;
	private String keycode;
	private String status;
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

	public void setLangs(List<LanguageBean> langs) {
		this.langs = langs;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public RcuServiceInfoBean(String roomNo, String keycode, String status, List<LanguageBean> langs) {
		super();
		this.roomNo = roomNo;
		this.keycode = keycode;
		this.status = status;
		this.langs = langs;
		this.time = new Date();
	}

}
