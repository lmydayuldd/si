package com.sidc.quartz.api.request;

import java.io.Serializable;

public class ScheduleStatusRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1679155192478607604L;
	private String jobname;
	
	public ScheduleStatusRequest(String jobname) {
		super();
		this.jobname = jobname;
	}

	public String getJobname() {
		return jobname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuartzNameRequest [jobname=");
		builder.append(jobname);
		builder.append("]");
		return builder.toString();
	}
	
}
