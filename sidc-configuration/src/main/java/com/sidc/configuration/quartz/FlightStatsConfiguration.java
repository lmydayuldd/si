package com.sidc.configuration.quartz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class FlightStatsConfiguration implements Serializable {
	private static final long serialVersionUID = -8018978639281470771L;
	private String url;
	private String applicationid;
	private List<FlightStatsKeys> keys = new ArrayList<FlightStatsKeys>();
	private List<FlightStatsApiMethod> methods = new ArrayList<FlightStatsApiMethod>();

	public String getUrl() {
		return url;
	}

	@XmlElementWrapper(name = "keys")
	@XmlElement(name = "property")
	public List<FlightStatsKeys> getKeys() {
		return keys;
	}

	public void setKeys(List<FlightStatsKeys> keys) {
		this.keys = keys;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApplicationid() {
		return applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	@XmlElement
	public List<FlightStatsApiMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<FlightStatsApiMethod> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsConfiguration [url=");
		builder.append(url);
		builder.append(", applicationid=");
		builder.append(applicationid);
		builder.append(", keys=");
		builder.append(keys);
		builder.append(", methods=");
		builder.append(methods);
		builder.append("]");
		return builder.toString();
	}

}
