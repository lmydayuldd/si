package com.sidc.blackcore.api.mobile.activity.bean;

import java.io.Serializable;

public class ActivityRepeatBean implements Serializable {
	private static final long serialVersionUID = -4437446065473618655L;

	private String starttime;
	private String endtime;
	private String description;

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getDescription() {
		return description;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityRepeatBean [starttime=");
		builder.append(starttime);
		builder.append(", endtime=");
		builder.append(endtime);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public ActivityRepeatBean(String starttime, String endtime, String description) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
		this.description = description;
	}

}
