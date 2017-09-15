package com.sidc.blackcore.api.mobile.activity.bean;

import java.io.Serializable;

public class ActivityTypeBean implements Serializable {
	private static final long serialVersionUID = -2815058503305258918L;
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
		builder.append("ActivityTypeBean [langcode=");
		builder.append(langcode);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public ActivityTypeBean(String langcode, String name, String description) {
		super();
		this.langcode = langcode;
		this.name = name;
		this.description = description;
	}

}
