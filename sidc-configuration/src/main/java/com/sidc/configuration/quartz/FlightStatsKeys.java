package com.sidc.configuration.quartz;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class FlightStatsKeys implements Serializable {

	private static final long serialVersionUID = 7268598636232721619L;
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	@XmlAttribute(name = "value")
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsKeys [name=");
		builder.append(name);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
