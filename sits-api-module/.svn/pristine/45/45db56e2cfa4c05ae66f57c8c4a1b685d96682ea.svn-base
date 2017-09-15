package com.sidc.quartz.api.response;

import java.io.Serializable;

public class ScheduleInfoResponse implements Serializable {
	private static final long serialVersionUID = -5661868845392297418L;
	private int status;
	private String jobname;
	private String groupname;
	private String executiontime;
	private String description;
	private String commands;

	public int getStatus() {
		return status;
	}

	public String getJobname() {
		return jobname;
	}

	public String getGroupname() {
		return groupname;
	}

	public String getExecutiontime() {
		return executiontime;
	}

	public String getDescription() {
		return description;
	}

	public String getCommands() {
		return commands;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleInfoResponse [status=");
		builder.append(status);
		builder.append(", jobname=");
		builder.append(jobname);
		builder.append(", groupname=");
		builder.append(groupname);
		builder.append(", executiontime=");
		builder.append(executiontime);
		builder.append(", description=");
		builder.append(description);
		builder.append(", commands=");
		builder.append(commands);
		builder.append("]");
		return builder.toString();
	}

	public ScheduleInfoResponse(int status, String jobname, String groupname, String executiontime, String description,
			String commands) {
		super();
		this.status = status;
		this.jobname = jobname;
		this.groupname = groupname;
		this.executiontime = executiontime;
		this.description = description;
		this.commands = commands;
	}

}
