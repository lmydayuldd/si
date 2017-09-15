package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.List;

public class FcmBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1125554872145342306L;
	private List<String> registration_ids;
	private String to;
	private Serializable data;

	public FcmBean(List<String> registration_ids, Serializable data) {
		super();
		this.registration_ids = registration_ids;
		this.data = data;
	}

	public FcmBean(String to) {
		super();
		this.to = to;
	}

	public FcmBean(List<String> registration_ids) {
		super();
		this.registration_ids = registration_ids;
	}

	public List<String> getRegistration_ids() {
		return registration_ids;
	}

	public String getTo() {
		return to;
	}

	public Serializable getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FcmBean [registration_ids=");
		builder.append(registration_ids);
		builder.append(", to=");
		builder.append(to);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
