package com.sidc.blackcore.api.ra.rcumodesetting.bean;

import java.io.Serializable;

public class RcuKeyCodeBean implements Serializable {
	private static final long serialVersionUID = 138655395380131233L;
	private String keycode;
	private Serializable condition;

	public String getKeycode() {
		return keycode;
	}

	public Serializable getCondition() {
		return condition;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuKeyCodeBean [keycode=");
		builder.append(keycode);
		builder.append(", condition=");
		builder.append(condition);
		builder.append("]");
		return builder.toString();
	}

	public RcuKeyCodeBean(String keycode, Serializable condition) {
		super();
		this.keycode = keycode;
		this.condition = condition;
	}

}
