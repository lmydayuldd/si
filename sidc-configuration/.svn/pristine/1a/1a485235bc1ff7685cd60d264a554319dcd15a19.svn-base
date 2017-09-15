package com.sidc.configuration.blackcore;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * @author Allen Huang
 *
 */
public class SidcUrlsLink implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3905560901460033872L;
	private String name;
	private String url;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name = "url")
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("SidcUrlsLink [name=\n");
		builder.append(name);
		builder.append(", url=\n");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}
}
