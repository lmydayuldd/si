package com.sidc.quartz.command;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.utils.exception.SiDCException;

public class Shutdown extends AbstractAPIProcess {

	public Shutdown() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.shutdown();
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
	}

}
