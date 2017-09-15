/**
 * 
 */
package com.sidc.rcu.engine.bean.config;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Nandin
 *
 */
public class RcuSystem implements Serializable {

	private static final long serialVersionUID = 6515752705168205108L;

	private String name;
	private String description;
	private boolean enable;
	private String path;

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute(name = "enable")
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@XmlElement(name = "path")
	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isEnable() {
		return enable;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuSystem [\n\tname=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", enable=");
		builder.append(enable);
		builder.append(", path=");
		builder.append(path);
		builder.append("]");
		return builder.toString();
	}

}
