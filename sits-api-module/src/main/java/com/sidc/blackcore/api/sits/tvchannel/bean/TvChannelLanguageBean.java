package com.sidc.blackcore.api.sits.tvchannel.bean;

import java.io.Serializable;

public class TvChannelLanguageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 985189596007309475L;
	private String name;
	private String lang;

	public String getName() {
		return name;
	}

	public String getLang() {
		return lang;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TvChannelLanguageBean [name=");
		builder.append(name);
		builder.append(", lang=");
		builder.append(lang);
		builder.append("]");
		return builder.toString();
	}

	public TvChannelLanguageBean(String name, String lang) {
		super();
		this.name = name;
		this.lang = lang;
	}

}
