package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;

public class LaundryTypeRequest implements Serializable {
	private static final long serialVersionUID = -2631526405914545436L;
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
		builder.append("LaundryTypeRequest [langcode=");
		builder.append(langcode);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public LaundryTypeRequest(String langcode, String status) {
		super();
		this.langcode = langcode;
		this.status = status;
	}

}
