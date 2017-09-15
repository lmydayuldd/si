package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;

public class RoomServiceLangBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7995108735816627769L;
	private String langcode;
	private String name;
	private String description;

	public String getLangcode() {
		return langcode;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceLangBean [langcode=");
		builder.append(langcode);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceLangBean(String langcode, String name, String description) {
		super();
		this.langcode = langcode;
		this.name = name;
		this.description = description;
	}

	public RoomServiceLangBean(String langcode, String name) {
		super();
		this.langcode = langcode;
		this.name = name;
	}

}
