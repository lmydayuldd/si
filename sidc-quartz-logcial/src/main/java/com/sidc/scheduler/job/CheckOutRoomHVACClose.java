package com.sidc.scheduler.job;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.api.sits.rcu.request.HvacCommandRequest;
import com.sidc.configuration.Configuration;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.Env;
import com.sidc.corejob.IJob;
import com.sidc.corejob.JobInfo;
import com.sidc.corejob.JobsAction;
import com.sidc.dao.quartz.manager.ScheduleManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.quartz.api.request.CheckOutRoomScheduleListRequest;
import com.sidc.quartz.api.request.ScheduleDataRequest;
import com.sidc.quartz.api.response.CheckOutRoomScheduleResposne;
import com.sidc.quartz.api.response.ScheduleDataResponse;
import com.sidc.rcu.sdk.command.HVACCommandClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class CheckOutRoomHVACClose extends JobsAction implements IJob {

	private String className = "";
	private ScheduleDataResponse info = null;
	private SidcUrlsLink sidcUrlsLink = null;
	private final static Logger logger = LoggerFactory.getLogger(CheckOutRoomHVACClose.class);
	
	public CheckOutRoomHVACClose() throws SQLException {
		// TODO Auto-generated constructor stub
		className = this.getClass().getSimpleName();
		ScheduleDataRequest request = new ScheduleDataRequest(className.substring(className.lastIndexOf('.') + 1));
		info = ScheduleManager.getInstance().select(request.getJobname());
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			SidcUrlsConfiguration sidcConfigure = Configuration
					.readSidcUrlsConfiguration(new File(Env.SYSTEM_DEF_PATH + Env.SIDC_URL_PATH));
			List<SidcUrlsLink> list = sidcConfigure.getUrls();
			sidcUrlsLink = new SidcUrlsLink();
			for (SidcUrlsLink link : list) {
				if (link.getName().equals("blackcore")) {
					sidcUrlsLink.setName(link.getName());
					sidcUrlsLink.setUrl(link.getUrl());
				}
			}
			
			final CheckOutRoomScheduleListRequest rooms = RoomManager.getInstance().listCheckOutRoom();
			for (CheckOutRoomScheduleResposne room : rooms.getList()) {
				HvacCommandRequest request = new HvacCommandRequest(room.getRoomno(), room.getIp(), false);
				new HVACCommandClient(sidcUrlsLink.getUrl(), request).execute();
				logger.debug("Room no " + room.getRoomno() + " HVAC close success");
			}
		} catch (Exception e) {
			logger.debug("HVAC close Error： " + e.getMessage());
		}
	}

	@Override
	public JobInfo setJobInfo() {
		// TODO Auto-generated method stub
		JobInfo job = new JobInfo();
		try {
			job.setJobName(info.getJobname()); // 設置任務名稱
			job.setJobGroup(info.getGroupname()); // 設置任務分組
			job.setTriggerType(0); // 設定任務執行計劃
									// 0：使用cron表達式執行，使用此項時cron表達式必填。
			job.setCron(info.getExecutiontime());
			job.setCronDescription(info.getDescription());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogAction.getInstance().debug("Close check-out room air condition job info Error： " + e.getMessage());
		}
		return job;
	}

	@Override
	protected void initial() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}

}
