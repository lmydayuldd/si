package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sidc.rcu.hmi.abs.AbstractRcuBean;

public class RcuHvacBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = 4232181234453370484L;
	private String roomNo;
	private List<RcuHvacInfoBean> list;
	private List<LanguageBean> langs;
	private Date time;

	public String getRoomNo() {
		return roomNo;
	}

	public List<RcuHvacInfoBean> getList() {
		return list;
	}

	public List<LanguageBean> getLangs() {
		return langs;
	}

	public Date getTime() {
		return time;
	}

	public RcuHvacBean(String roomNo, List<RcuHvacInfoBean> list, List<LanguageBean> langs) {
		super();
		this.roomNo = roomNo;
		this.list = list;
		this.langs = langs;
		this.time = new Date();
	}

}
