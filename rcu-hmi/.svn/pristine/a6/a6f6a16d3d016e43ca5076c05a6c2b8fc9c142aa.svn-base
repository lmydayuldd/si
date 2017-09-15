package com.sidc.rcu.hmi.bean;

import java.io.Serializable;
import java.util.List;

public class RcuDeviceBean implements Serializable {
	private static final long serialVersionUID = 8377404567590695828L;
	private String keycode;
	private RcuStatusBean condition;
	private List<LanguageBean> langs;

	public String getKeycode() {
		return keycode;
	}

	public RcuStatusBean getCondition() {
		return condition;
	}

	public List<LanguageBean> getLangs() {
		return langs;
	}

	public RcuDeviceBean(String keycode, RcuStatusBean condition, List<LanguageBean> langs) {
		super();
		this.keycode = keycode;
		this.condition = condition;
		this.langs = langs;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public void setCondition(RcuStatusBean condition) {
		this.condition = condition;
	}

	public void setLangs(List<LanguageBean> langs) {
		this.langs = langs;
	}

}
