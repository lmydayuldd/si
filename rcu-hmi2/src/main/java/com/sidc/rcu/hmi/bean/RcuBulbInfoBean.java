package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.framework.abs.AbstractRcuBean;

public class RcuBulbInfoBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = 4002518611814088758L;
	private String roomNo;
	private List<RcuDeviceBean> list;
	private List<LanguageBean> langs;
	private Date time;

	public String getRoomNo() {
		return roomNo;
	}

	public List<RcuDeviceBean> getList() {
		return list;
	}

	public List<LanguageBean> getLangs() {
		return langs;
	}

	public Date getTime() {
		return time;
	}

	public RcuBulbInfoBean(String roomNo, List<RcuDeviceBean> list, List<LanguageBean> langs) {
		super();
		this.roomNo = roomNo;
		this.list = list;
		this.langs = langs;
		this.time = new Date();
	}

}
