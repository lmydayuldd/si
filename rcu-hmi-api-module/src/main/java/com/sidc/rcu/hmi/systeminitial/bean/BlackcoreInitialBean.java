package com.sidc.rcu.hmi.systeminitial.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class BlackcoreInitialBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5928289580433697596L;
	private String url;

	public String getUrl() {
		return url;
	}

	@XmlElement(name = "url")
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlackcoreInitialBean [url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}
}
