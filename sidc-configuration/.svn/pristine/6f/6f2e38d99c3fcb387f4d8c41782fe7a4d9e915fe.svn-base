package com.sidc.configuration.blackcore;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement(name = "configuration")
public class AppFcmKeyConfiguration implements Serializable {
	private static final long serialVersionUID = -7900923920829140135L;
	private List<AppFcmKey> keys;

	@XmlElementWrapper(name = "keys")
	@XmlElement(name = "app")
	public List<AppFcmKey> getKeys() {
		return keys;
	}

	public void setKeys(List<AppFcmKey> keys) {
		this.keys = keys;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppFcmKeyConfiguration [keys=");
		builder.append(keys);
		builder.append("]");
		return builder.toString();
	}
	
}
