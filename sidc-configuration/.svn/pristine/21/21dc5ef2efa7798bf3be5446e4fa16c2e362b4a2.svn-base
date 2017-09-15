package com.sidc.configuration.blackcore;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author Allen Huang
 *
 */
public class AllowDomainIP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204765109819766139L;
	private List<String> value;
	
	@XmlElement(name = "value")
	public void setValue(List<String> value) {
		this.value = value;
	}

	public List<String> getValue() {
		return value;
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("AllowDomainIP [value=\n");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
