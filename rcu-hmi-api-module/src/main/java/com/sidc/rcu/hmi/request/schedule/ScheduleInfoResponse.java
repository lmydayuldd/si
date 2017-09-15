package com.sidc.rcu.hmi.request.schedule;

import java.io.Serializable;

public class ScheduleInfoResponse implements Serializable {
	private static final long serialVersionUID = 2947798863923222327L;
	private int status;
	private String executiontime;
	private ScheduleInfoDescriptionBean description;

	public int getStatus() {
		return status;
	}

	public String getExecutiontime() {
		return executiontime;
	}

	public ScheduleInfoDescriptionBean getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleInfoResponse [status=");
		builder.append(status);
		builder.append(", executiontime=");
		builder.append(executiontime);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public ScheduleInfoResponse(int status, String executiontime, ScheduleInfoDescriptionBean description) {
		super();
		this.status = status;
		this.executiontime = executiontime;
		this.description = description;
	}

}
