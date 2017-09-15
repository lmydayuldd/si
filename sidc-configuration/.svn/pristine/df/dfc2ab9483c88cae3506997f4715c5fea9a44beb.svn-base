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
public class AllowDomainConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1480844985450538302L;
	private List<AllowDomainIP> list;
	
	@XmlElementWrapper(name = "property")
	@XmlElement(name = "list")
	public List<AllowDomainIP> getList() {
		return list;
	}
	public void setList(List<AllowDomainIP> list) {
		this.list = list;
	}

	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("AllowDomainConfiguration [list=\n");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
}
