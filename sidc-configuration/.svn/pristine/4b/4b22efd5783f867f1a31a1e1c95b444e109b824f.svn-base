/**
 * 
 */
package com.sidc.rcu.engine.bean.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Nandin
 *
 */
public class RCUService implements java.io.Serializable {

	private static final long serialVersionUID = 4734364042109897712L;
	private String name;
	private String description;
	private int mainport;
	private int target;
	private int noticeHostTimer;

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "description")
	public String getDescription() {
		return description;
	}

	@XmlElement(name = "mainport")
	public void setMainport(int mainport) {
		this.mainport = mainport;
	}

	@XmlElement(name = "target")
	public void setTarget(int target) {
		this.target = target;
	}

	@XmlAttribute(name = "noticeHostTimer")
	public void setNoticeHostTimer(int noticeHostTimer) {
		this.noticeHostTimer = noticeHostTimer;
	}

	public String getName() {
		return name;
	}

	public int getMainport() {
		return mainport;
	}

	public int getTarget() {
		return target;
	}

	public int getNoticeHostTimer() {
		return noticeHostTimer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCUService [\n\tname=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", mainport=");
		builder.append(mainport);
		builder.append(", target=");
		builder.append(target);
		builder.append(", noticeHostTimer=");
		builder.append(noticeHostTimer);
		builder.append("]");
		return builder.toString();
	}

}
