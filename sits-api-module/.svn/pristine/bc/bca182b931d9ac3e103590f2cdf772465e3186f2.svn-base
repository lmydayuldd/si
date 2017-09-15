package com.sidc.quartz.api.request;

import java.io.Serializable;

public class ScheduleCommandRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5183826365952103799L;
	private String command;
	private String jobname;
	private String groupname;
	private String cronstring;
	private String description;
	
	public ScheduleCommandRequest(String command, String jobname, String groupname, String cronstring, String description) {
		// TODO Auto-generated constructor stub
		this.command = command;
		this.jobname = jobname;
		this.groupname = groupname;
		this.cronstring = cronstring;
		this.description = description;
	}
	
	public ScheduleCommandRequest(String command, String jobname, String groupname) {
		this.command = command;
		this.jobname = jobname;
		this.groupname = groupname;
	}
	
	public ScheduleCommandRequest(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public String getJobname() {
		return jobname;
	}

	public String getGroupname() {
		return groupname;
	}

	public String getCronstring() {
		return cronstring;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleCommandRequest [command=");
		builder.append(command);
		builder.append(", jobname=");
		builder.append(jobname);
		builder.append(", groupname=");
		builder.append(groupname);
		builder.append(", cronstring=");
		builder.append(cronstring);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
}
