package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;

public class LaundryClassRequest implements Serializable {

	private static final long serialVersionUID = 1177046916877211035L;
	private String langcode;
	private String status;

	public String getLangcode() {
		return langcode;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryClassRequest [langcode=");
		builder.append(langcode);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public LaundryClassRequest(String langcode, String status) {
		super();
		this.langcode = langcode;
		this.status = status;
	}

}
