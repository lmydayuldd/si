package com.sidc.rcu.hmi.websocket.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.bean.LanguageBean;

public class IndexServiceWebsoketBean implements Serializable {
	private static final long serialVersionUID = -7274066531290527887L;
	private String roomno;
	private String keycode;
	private String status;
	private List<LanguageBean> langs;
	private int lostminute;
	private Date time;
	private Serializable condition;

	public String getRoomno() {
		return roomno;
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

	public int getLostminute() {
		return lostminute;
	}

	public Date getTime() {
		return time;
	}

	public Serializable getCondition() {
		return condition;
	}

	public IndexServiceWebsoketBean(String roomno, String keycode, String status, List<LanguageBean> langs,
			int lostminute, Date time, Serializable condition) {
		super();
		this.roomno = roomno;
		this.keycode = keycode;
		this.status = status;
		this.langs = langs;
		this.lostminute = lostminute;
		this.time = time;
		this.condition = condition;
	}

	public IndexServiceWebsoketBean(String roomno, String keycode, String status, List<LanguageBean> langs,
			int lostminute, Date time) {
		super();
		this.roomno = roomno;
		this.keycode = keycode;
		this.status = status;
		this.langs = langs;
		this.lostminute = lostminute;
		this.time = time;
	}

	public IndexServiceWebsoketBean(String keycode, String status) {
		super();
		this.keycode = keycode;
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IndexServiceWebsoketBean [roomno=");
		builder.append(roomno);
		builder.append(", keycode=");
		builder.append(keycode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", langs=");
		builder.append(langs);
		builder.append(", lostminute=");
		builder.append(lostminute);
		builder.append(", time=");
		builder.append(time);
		builder.append(", condition=");
		builder.append(condition);
		builder.append("]");
		return builder.toString();
	}

}
