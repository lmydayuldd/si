package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;

public class LaundryReturnTypeRequest implements Serializable {
	private static final long serialVersionUID = -331482819605056679L;
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
		builder.append("LaundryReturnTypeRequest [langcode=");
		builder.append(langcode);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public LaundryReturnTypeRequest(String langcode, String status) {
		super();
		this.langcode = langcode;
		this.status = status;
	}

}
