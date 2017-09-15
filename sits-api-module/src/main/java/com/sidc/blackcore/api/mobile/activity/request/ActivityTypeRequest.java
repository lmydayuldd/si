package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;

public class ActivityTypeRequest implements Serializable {
	private static final long serialVersionUID = 4683649214729127520L;
	private String token;
	private String langcode;
	private String status;

	public String getToken() {
		return token;
	}

	public String getLangcode() {
		return langcode;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityTypeRequest [token=");
		builder.append(token);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public ActivityTypeRequest(String token, String langcode, String status) {
		super();
		this.token = token;
		this.langcode = langcode;
		this.status = status;
	}

}
