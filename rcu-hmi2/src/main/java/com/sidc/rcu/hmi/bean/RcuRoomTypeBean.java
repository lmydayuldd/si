package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.framework.abs.AbstractRcuBean;

public class RcuRoomTypeBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = -8890050681459250032L;

	private String roomNo;
	private String type;
	private List<LanguageBean> langs;
	private Date time;

	public String getRoomNo() {
		return roomNo;
	}

	public String getType() {
		return type;
	}

	public List<LanguageBean> getLangs() {
		return langs;
	}

	public Date getTime() {
		return time;
	}
	
	public RcuRoomTypeBean(String roomNo, String type) {
		super();
		this.roomNo = roomNo;
		this.type = type;
		this.time = new Date();
	}

}
