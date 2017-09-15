package com.sidc.corejob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sidc.utils.exception.SiDCException;

public abstract class JobsAction extends TasksAction implements Job {

	protected void doJob() throws SiDCException, Exception {
		initial();
		
		setJobInfo();
	}
	
	@Override
	public abstract void execute(JobExecutionContext context) throws JobExecutionException;
	
	protected abstract JobInfo setJobInfo() throws SiDCException, Exception;

	protected abstract void initial() throws SiDCException, Exception;
}
