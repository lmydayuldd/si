package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.abs.AbstractRcuBean;

public class RcuBulbInfoBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = 4002518611814088758L;
	private String roomNo;
	private String keycode;
	private String status;
	private List<LanguageBean> langs;
	private Date time;

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getKeycode() {
		return keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public RcuBulbInfoBean(String roomNo, String keycode, List<LanguageBean> langs) {
		super();
		this.roomNo = roomNo;
		this.keycode = keycode;
		this.langs = langs;
		this.time = new Date();
	}

}
