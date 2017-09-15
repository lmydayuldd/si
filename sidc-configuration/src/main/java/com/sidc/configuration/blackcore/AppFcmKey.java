package com.sidc.configuration.blackcore;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class AppFcmKey implements Serializable {

	private static final long serialVersionUID = -7733455560965968341L;
	private String name;
	private String key;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppFcmKey [name=");
		builder.append(name);
		builder.append(", key=");
		builder.append(key);
		builder.append("]");
		return builder.toString();
	}

}
