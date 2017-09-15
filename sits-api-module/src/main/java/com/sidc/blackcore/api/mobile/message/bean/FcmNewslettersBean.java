package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FcmNewslettersBean implements Serializable {
	private static final long serialVersionUID = -2257675922267384258L;
	private List<String> registration_id = new ArrayList<String>();
	private String to;
	private FcmNewslettersInfoBean data;

	public List<String> getRegistration_id() {
		return registration_id;
	}

	public String getTo() {
		return to;
	}

	public FcmNewslettersInfoBean getData() {
		return data;
	}

	public void setData(FcmNewslettersInfoBean data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FcmNewslettersBean [registration_id=");
		builder.append(registration_id);
		builder.append(", to=");
		builder.append(to);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

	public FcmNewslettersBean(List<String> registration_id, String to, FcmNewslettersInfoBean data) {
		super();
		this.registration_id = registration_id;
		this.to = to;
		this.data = data;
	}

	public FcmNewslettersBean(String to) {
		super();
		this.to = to;
	}

	public FcmNewslettersBean(List<String> registration_id) {
		super();
		this.registration_id = registration_id;
	}

}
