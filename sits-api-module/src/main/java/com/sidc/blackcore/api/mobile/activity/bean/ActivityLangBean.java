package com.sidc.blackcore.api.mobile.activity.bean;

import java.io.Serializable;

public class ActivityLangBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3589539707602827306L;
	private String langcode;
	private String title;
	private String content;
	private String location;

	public String getLangcode() {
		return langcode;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityLangBean [langcode=");
		builder.append(langcode);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", location=");
		builder.append(location);
		builder.append("]");
		return builder.toString();
	}

	public ActivityLangBean(String langcode, String title, String content, String location) {
		super();
		this.langcode = langcode;
		this.title = title;
		this.content = content;
		this.location = location;
	}

}
