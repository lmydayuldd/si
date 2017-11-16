package com.sidc.quartz.api.request;

import java.io.Serializable;

public class ScheduleDataRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4056057616236225874L;
	private String jobname;
	
	public ScheduleDataRequest(String jobname) {
		super();
		this.jobname = jobname;
	}

	public String getJobname() {
		return jobname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleDataRequest [jobname=");
		builder.append(jobname);
		builder.append("]");
		return builder.toString();
	}
	
}
