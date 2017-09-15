package com.sidc.corejob;

import java.util.Date;

/**
 * 
 * @author Allen Huang
 *
 */
public class JobInfo {
	private String jobName;
	private String jobGroup;
	private Integer triggerType;
	private Date startTime;
	private String cron;
	private String cronDescription;

	public String getCronDescription() {
		return this.cronDescription;
	}

	public void setCronDescription(String cronDescription) {
		this.cronDescription = cronDescription;
	}

	public String getJobName() {
		if (this.jobName == null) {
			return "Job Name" + String.valueOf(java.lang.Math.random()).split("\\.")[1];
		}
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		if (this.jobGroup == null) {
			return "Job Group" + String.valueOf(java.lang.Math.random()).split("\\.")[1];
		}
		return this.jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public Integer getTriggerType() {
		return this.triggerType;
	}

	public void setTriggerType(Integer triggerType) {
		this.triggerType = triggerType;
	}

	public String getCron() {
		return this.cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public Date getStartTime() {
		if (this.startTime == null) {
			return new Date();
		}
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}