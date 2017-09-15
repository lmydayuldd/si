package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;

public class ActivityFeeRequest implements Serializable {
	private static final long serialVersionUID = 6516044562228803163L;
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
		builder.append("ActivityFeeRequest [token=");
		builder.append(token);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public ActivityFeeRequest(String token, String langcode, String status) {
		super();
		this.token = token;
		this.langcode = langcode;
		this.status = status;
	}

}
