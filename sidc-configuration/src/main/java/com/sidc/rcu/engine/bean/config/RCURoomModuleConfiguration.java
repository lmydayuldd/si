package com.sidc.rcu.engine.bean.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement(name = "configuration")
public class RCURoomModuleConfiguration implements java.io.Serializable {

	private boolean isEnable;

	@XmlElement(name = "initialEnable")
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isEnable() {
		return isEnable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCURoomModuleConfiguration [isEnable=");
		builder.append(isEnable);
		builder.append("]");
		return builder.toString();
	}

}
