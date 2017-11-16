package com.sidc.quartz.command;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.Configuration;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.Env;
import com.sidc.corejob.common.Tool;
import com.sidc.quartz.api.request.ScheduleCommandRequest;
import com.sidc.quartz.api.request.ScheduleUpdateDataRequest;
import com.sidc.quartz.sdk.ScheduleUpdateClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class ReScheduleJob extends AbstractAPIProcess {

	private ScheduleCommandRequest enity;

	public ReScheduleJob(ScheduleCommandRequest enity) {
		// TODO Auto-generated constructor stub
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
		try {
			ScheduleUpdateDataRequest request = new ScheduleUpdateDataRequest(this.enity.getJobname(),
					this.enity.getCronstring(), this.enity.getDescription());
			SidcUrlsConfiguration sidcConfigure = Configuration
					.readSidcUrlsConfiguration(new File(Env.SYSTEM_DEF_PATH + Env.SIDC_URL_PATH));
			List<SidcUrlsLink> list = sidcConfigure.getUrls();
			SidcUrlsLink sidcUrlsLink = new SidcUrlsLink();
			for (SidcUrlsLink link : list) {
				if (link.getName().equals("quartz")) {
					sidcUrlsLink.setName(link.getName());
					sidcUrlsLink.setUrl(link.getUrl());
				}
			}
			new ScheduleUpdateClient<Object>(sidcUrlsLink.getUrl(), request).execute();

			new PauseJob(this.enity).execute();
			
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobKey jobKey = JobKey.jobKey(this.enity.getJobname(), this.enity.getGroupname());
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			Trigger trigger = Tool.getCronTrigger(null, this.enity.getCronstring(), this.enity.getDescription());
			scheduler.unscheduleJob(TriggerKey.triggerKey(this.enity.getJobname(), this.enity.getGroupname()));
			scheduler.deleteJob(jobKey);
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			LogAction.getInstance().error("ReScheduleJob Error:", e);
		}
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Rquest is illegal");
		}

		if (StringUtils.isBlank(this.enity.getJobname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Job name is empty");
		}

		if (StringUtils.isBlank(this.enity.getGroupname())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Group name is empty");
		}

		if (StringUtils.isBlank(this.enity.getCronstring())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Cron string is empty");
		}
	}
}
