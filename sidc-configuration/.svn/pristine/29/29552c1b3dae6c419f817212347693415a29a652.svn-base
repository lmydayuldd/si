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
public class RmiInterface implements Serializable {

	private static final long serialVersionUID = -5639396156951656180L;

	private String name;
	private String classname;

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return classname;
	}

	@XmlElement(name = "classname")
	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RmiInterface [\n\tname=");
		builder.append(name);
		builder.append(", classname=");
		builder.append(classname);
		builder.append("]");
		return builder.toString();
	}

}
