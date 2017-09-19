package com.sidc.rcu.hmi.schedule.request;

import java.io.Serializable;

public class ScheduleCommandRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7967212522226867068L;
	private String command;
	private String jobname;
	private String jobgroup;

	public String getCommand() {
		return command;
	}

	public String getJobname() {
		return jobname;
	}

	public String getJobgroup() {
		return jobgroup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleCommandRequest [command=");
		builder.append(command);
		builder.append(", jobname=");
		builder.append(jobname);
		builder.append(", jobgroup=");
		builder.append(jobgroup);
		builder.append("]");
		return builder.toString();
	}

	public ScheduleCommandRequest(String command, String jobname, String jobgroup) {
		super();
		this.command = command;
		this.jobname = jobname;
		this.jobgroup = jobgroup;
	}

}
