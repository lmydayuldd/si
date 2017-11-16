package com.sidc.rcu.hmi.systeminitial.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class SosRecordInitialBean implements Serializable {

	private static final long serialVersionUID = -6849870624182639109L;
	private int stopalarmminute;

	public int getStopalarmminute() {
		return stopalarmminute;
	}

	@XmlElement(name = "stop-alarm-minute")
	public void setStopalarmminute(int stopalarmminute) {
		this.stopalarmminute = stopalarmminute;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SosRecordInitialBean [stopalarmminute=");
		builder.append(stopalarmminute);
		builder.append("]");
		return builder.toString();
	}

}
