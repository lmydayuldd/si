package com.sidc.configuration.blackcore;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Allen Huang
 *
 */
@XmlRootElement(name = "configuration")
public class SidcUrlsConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -234519809374148439L;
	private List<SidcUrlsLink> urls;

	@XmlElementWrapper(name = "urls")
	@XmlElement(name = "link")
	public List<SidcUrlsLink> getUrls() {
		return urls;
	}
	
	public void setUrls(List<SidcUrlsLink> urls) {
		this.urls = urls;
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("SidcUrlsConfiguration [urls=\n");
		builder.append(urls);
		builder.append("]");
		return builder.toString();
	}
}
