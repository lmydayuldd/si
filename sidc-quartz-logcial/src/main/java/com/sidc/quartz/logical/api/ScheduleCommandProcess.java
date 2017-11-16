package com.sidc.quartz.logical.api;


import org.apache.commons.lang3.StringUtils;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.quartz.api.command.key.QuartzCommand;
import com.sidc.quartz.api.request.ScheduleCommandRequest;
import com.sidc.quartz.command.DeleteJob;
import com.sidc.quartz.command.PauseJob;
import com.sidc.quartz.command.QueryJob;
import com.sidc.quartz.command.ReScheduleJob;
import com.sidc.quartz.command.ResumeJob;
import com.sidc.quartz.command.Shutdown;
import com.sidc.quartz.command.StartJob;
import com.sidc.quartz.command.StartUp;
import com.sidc.quartz.command.TriggerJob;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class ScheduleCommandProcess extends AbstractAPIProcess {

	private ScheduleCommandRequest enity;

	public ScheduleCommandProcess(ScheduleCommandRequest enity) {
		this.enity = enity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + this.enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		LogAction.getInstance().info(this.enity.getCommand());
		switch (QuartzCommand.valueOf(this.enity.getCommand())) {
		case QUERY:
			return new QueryJob().execute();
		case PAUSE:
			return new PauseJob(this.enity).execute();
		case RESUME:
			return new ResumeJob(this.enity).execute();
		case TRIGGER:
			return new TriggerJob(this.enity).execute();
		case RESCHEDULE:
			return new ReScheduleJob(this.enity).execute();
		case DELETE:
			return new DeleteJob(this.enity).execute();
//		case STARTJOB:
//			return new StartJob(this.enity).execute();
		case STARTUP:
			return new StartUp().execute();
		case SHUTDOWN:
			return new Shutdown().execute();
		default:
			break;
		}
		
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Rquest is illegal");
		}
		
		if (StringUtils.isBlank(this.enity.getCommand())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Quartz command is empty");
		}
		
		switch (QuartzCommand.valueOf(this.enity.getCommand())) {
		case PAUSE:
		case RESUME:
		case TRIGGER:
		case DELETE:
		case STARTJOB:
			if (StringUtils.isBlank(this.enity.getJobname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Job name is empty");
			}
			if (StringUtils.isBlank(this.enity.getGroupname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Group name is empty");
			}
			break;
		case RESCHEDULE:
			if (StringUtils.isBlank(this.enity.getJobname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Job name is empty");
			}
			if (StringUtils.isBlank(this.enity.getGroupname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Group name is empty");
			}
			if (StringUtils.isBlank(this.enity.getCronstring())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Cron string is empty");
			}
			break;
		default:
			break;
		}
	}

}
