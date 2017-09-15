/**
 * 
 */
package com.sidc.configuration.quartz;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Joe
 *
 */
public class FlightSourceConfiguration implements java.io.Serializable {
	private String name;
	private String path;
	private boolean enable;

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

	@XmlAttribute(name = "path")
	public void setPath(String path) {
		this.path = path;
	}

	public boolean isEnable() {
		return enable;
	}

	@XmlAttribute(name = "enable")
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightSourceConfiguration [name=");
		builder.append(name);
		builder.append(", path=");
		builder.append(path);
		builder.append(", enable=");
		builder.append(enable);
		builder.append("]");
		return builder.toString();
	}

}
