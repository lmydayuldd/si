package com.sidc.blackcore.api.mobile.laundry.request;

import java.io.Serializable;

public class LaundryItemRequest implements Serializable {

	private static final long serialVersionUID = 2963974605869282437L;
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
		builder.append("LaundryItemRequest [langcode=");
		builder.append(langcode);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public LaundryItemRequest(String langcode, String status) {
		super();
		this.langcode = langcode;
		this.status = status;
	}

}
