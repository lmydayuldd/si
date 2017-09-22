package com.sidc.rcu.hmi.receiver.bean;

import java.io.Serializable;

public class PmsReceiverBean implements Serializable {
	private static final long serialVersionUID = -5052820439961015968L;
	private String keycode;

	public String getKeycode() {
		return keycode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PmsReceiverBean [keycode=");
		builder.append(keycode);
		builder.append("]");
		return builder.toString();
	}

	public PmsReceiverBean(String keycode) {
		super();
		this.keycode = keycode;
	}

}
