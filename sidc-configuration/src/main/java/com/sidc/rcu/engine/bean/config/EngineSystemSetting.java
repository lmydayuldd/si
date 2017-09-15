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
public class EngineSystemSetting implements Serializable {

	private static final long serialVersionUID = 5087484005272802204L;
	private String name;
	private String value;
	private String description;
	private String unit;

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "value")
	public void setValue(String value) {
		this.value = value;
	}

	@XmlAttribute(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute(name = "unit")
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public String getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EngineSystemSetting [\n\tname=");
		builder.append(name);
		builder.append(", value=");
		builder.append(value);
		builder.append(", description=");
		builder.append(description);
		builder.append(", unit=");
		builder.append(unit);
		builder.append("]");
		return builder.toString();
	}

}
