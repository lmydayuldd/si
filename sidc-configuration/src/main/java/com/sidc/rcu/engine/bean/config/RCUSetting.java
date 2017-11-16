package com.sidc.rcu.engine.bean.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class RCUSetting implements java.io.Serializable {

	private static final long serialVersionUID = -7332263326035315881L;
	private String modulePath;
	private List<RCUService> services;
	private String description;
	private String modePath;

	@XmlElement(name = "room-module")
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	@XmlElementWrapper(name = "servers")
	@XmlElement(name = "server")
	public void setService(List<RCUService> services) {
		this.services = services;
	}

	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public List<RCUService> getService() {
		return services;
	}

	public String getModulePath() {
		return modulePath;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "mode")
	public void setModePath(String modePath) {
		this.modePath = modePath;
	}

	public String getModePath() {
		return modePath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCUSetting [modulePath=");
		builder.append(modulePath);
		builder.append(", services=");
		builder.append(services);
		builder.append(", description=");
		builder.append(description);
		builder.append(", modePath=");
		builder.append(modePath);
		builder.append("]");
		return builder.toString();
	}

}
