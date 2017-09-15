package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;

public class RoomServiceRequest implements Serializable {
	private static final long serialVersionUID = 7313896503057207623L;
	private String langcode;

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceRequest [langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceRequest(String langcode) {
		super();
		this.langcode = langcode;
	}

}
