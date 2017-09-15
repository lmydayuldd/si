package com.sidc.quartz.api.response;

import java.io.Serializable;

public class ScheduleStateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8678230957448131747L;
	private String jobname;
	private String jobgroup;
	private String nextfiretime;
	private String prefiretime;
	private String during;
	private String description;
	private String starttime;
	private String state;
	
	public ScheduleStateResponse(String jobname, String jobgroup, String nextfiretime, String prefiretime, String during,
			String description, String starttime, String state) {
		super();
		this.jobname = jobname;
		this.jobgroup = jobgroup;
		this.nextfiretime = nextfiretime;
		this.prefiretime = prefiretime;
		this.during = during;
		this.description = description;
		this.starttime = starttime;
		this.state = state;
	}
	public String getJobname() {
		return jobname;
	}

	public String getJobgroup() {
		return jobgroup;
	}

	public String getNextfiretime() {
		return nextfiretime;
	}

	public String getPrefiretime() {
		return prefiretime;
	}

	public String getDuring() {
		return during;
	}

	public String getDescription() {
		return description;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getState() {
		return state;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuartzStateResponse [jobname=");
		builder.append(jobname);
		builder.append(", jobgroup=");
		builder.append(jobgroup);
		builder.append(", nextfiretime=");
		builder.append(nextfiretime);
		builder.append(", prefiretime=");
		builder.append(prefiretime);
		builder.append(", during=");
		builder.append(during);
		builder.append(", description=");
		builder.append(description);
		builder.append(", starttime=");
		builder.append(starttime);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}
	
}
