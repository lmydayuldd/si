package com.sidc.rcu.hmi.schedule.request;

import java.io.Serializable;

public class ScheduleInfoBean implements Serializable {
	private static final long serialVersionUID = 1243884132971410794L;
	private int status;
	private String executiontime;
	private String description;

	public int getStatus() {
		return status;
	}

	public String getExecutiontime() {
		return executiontime;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleInfoBean [status=");
		builder.append(status);
		builder.append(", executiontime=");
		builder.append(executiontime);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public ScheduleInfoBean(int status, String executiontime, String description) {
		super();
		this.status = status;
		this.executiontime = executiontime;
		this.description = description;
	}

}
