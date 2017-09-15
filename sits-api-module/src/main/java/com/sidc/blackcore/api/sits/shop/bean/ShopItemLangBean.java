package com.sidc.blackcore.api.sits.shop.bean;

import java.io.Serializable;

public class ShopItemLangBean implements Serializable {
	private static final long serialVersionUID = -2292918956619714441L;
	private String langcode;
	private String name;
	private String description;
	private String introduction;

	public String getLangcode() {
		return langcode;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getIntroduction() {
		return introduction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopItemLangBean [langcode=");
		builder.append(langcode);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", introduction=");
		builder.append(introduction);
		builder.append("]");
		return builder.toString();
	}

	public ShopItemLangBean(String langcode, String name, String description, String introduction) {
		super();
		this.langcode = langcode;
		this.name = name;
		this.description = description;
		this.introduction = introduction;
	}
}
