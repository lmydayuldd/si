package com.sidc.quartz.api.request;

import java.io.Serializable;

public class ScheduleUpdateDataRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2372898386320588322L;
	private String jobname;
	private String groupname;
	private int status;
	private String executiontime;
	private String description;
	private String commands;

	public ScheduleUpdateDataRequest(String jobname, String groupname, int status, String executiontime,
			String description, String commands) {
		super();
		this.jobname = jobname;
		this.groupname = groupname;
		this.status = status;
		this.executiontime = executiontime;
		this.description = description;
		this.commands = commands;
	}

	public ScheduleUpdateDataRequest(String jobname, String groupname, int status, String executiontime,
			String commands) {
		super();
		this.jobname = jobname;
		this.groupname = groupname;
		this.status = status;
		this.executiontime = executiontime;
		this.commands = commands;
	}

	public ScheduleUpdateDataRequest(String jobname, int status, String executiontime, String description) {
		super();
		this.jobname = jobname;
		this.status = status;
		this.executiontime = executiontime;
		this.description = description;
	}

	public String getJobname() {
		return jobname;
	}

	public String getGroupname() {
		return groupname;
	}

	public int getStatus() {
		return status;
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
		builder.append("ScheduleUpdateDataRequest [jobname=");
		builder.append(jobname);
		builder.append(", groupname=");
		builder.append(groupname);
		builder.append(", status=");
		builder.append(status);
		builder.append(", executiontime=");
		builder.append(executiontime);
		builder.append(", description=");
		builder.append(description);
		builder.append(", commands=");
		builder.append(commands);
		builder.append("]");
		return builder.toString();
	}
}
