package com.sidc.quartz.command;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.corejob.Core;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class RefreshJob extends AbstractAPIProcess {
	
	public RefreshJob() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		try {
			Core core = new Core();
			core.initJob();
		} catch (Exception e) {
			LogAction.getInstance().error("RefreshJob Error:", e);
		}
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
	}

}
