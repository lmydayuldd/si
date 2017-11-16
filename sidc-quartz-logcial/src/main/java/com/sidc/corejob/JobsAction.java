package com.sidc.corejob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sidc.utils.exception.SiDCException;

public abstract class JobsAction implements Job {

	protected void doJob() throws SiDCException, Exception {
		initial();
		
		setJobInfo();
	}
	
	@Override
	public abstract void execute(JobExecutionContext context) throws JobExecutionException;
	
	public abstract JobInfo setJobInfo();

	protected abstract void initial() throws SiDCException, Exception;
}
