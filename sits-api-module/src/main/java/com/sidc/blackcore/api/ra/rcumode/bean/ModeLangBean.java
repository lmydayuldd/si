/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;

public class ModeLangBean implements Serializable {
	private static final long serialVersionUID = -532665346915573943L;
	private String lang;
	private String name;
	private String description;

	public String getLang() {
		return lang;
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
		builder.append("ModeLangBean [lang=");
		builder.append(lang);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public ModeLangBean(String lang, String name, String description) {
		super();
		this.lang = lang;
		this.name = name;
		this.description = description;
	}

}
