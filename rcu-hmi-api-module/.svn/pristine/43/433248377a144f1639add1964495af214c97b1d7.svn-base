package com.sidc.rcu.hmi.systeminitial.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class WebsocketInitialServiceBean implements Serializable {
	private static final long serialVersionUID = -6454208570018791323L;
	private String name;
	private String path;

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	@XmlElement(name = "path")
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebsocketInitialServiceBean [name=");
		builder.append(name);
		builder.append(", path=");
		builder.append(path);
		builder.append("]");
		return builder.toString();
	}

}
