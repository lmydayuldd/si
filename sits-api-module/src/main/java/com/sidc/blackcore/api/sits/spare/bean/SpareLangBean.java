package com.sidc.blackcore.api.sits.spare.bean;

import java.io.Serializable;

public class SpareLangBean implements Serializable {
	private static final long serialVersionUID = 4381338944324704061L;
	private String name;
	private String langcode;
	private String description;

	public String getName() {
		return name;
	}

	public String getLangcode() {
		return langcode;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpareLangBean [name=");
		builder.append(name);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public SpareLangBean(String name, String langcode, String description) {
		super();
		this.name = name;
		this.langcode = langcode;
		this.description = description;
	}

}
