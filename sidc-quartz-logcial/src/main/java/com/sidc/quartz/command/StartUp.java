package com.sidc.quartz.command;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.utils.exception.SiDCException;

public class StartUp extends AbstractAPIProcess {

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.start();
		new RefreshJob().execute();
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
	}

}
