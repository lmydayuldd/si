package com.sidc.corejob;

import static org.quartz.JobBuilder.newJob;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.corejob.common.ReflectInterface;
import com.sidc.corejob.common.Tool;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * Job核心控制
 * 
 * @author Allen Huang
 *
 */
public class Core implements ServletContextListener {

	private Scheduler scheduler;
	private final static Logger logger = LoggerFactory.getLogger(Core.class);

	public Core() throws SiDCException, Exception {
		// TODO Auto-generated constructor stub
		initial();
	}
	
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent arg0) {
		this.initJob();
	}

	@SuppressWarnings("unchecked")
	public void initJob() {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			ReflectInterface reflectInterface = new ReflectInterface();
			List<Class<?>> ijobList = reflectInterface.getAllAssignedClass(JobsAction.class);			
			
			for (Class<?> job : ijobList) {
				JobsAction jobsAction = (JobsAction) job.newInstance();
				Class<? extends Job> jobClass = (Class<? extends Job>) job;
				// 查詢此作業是否正在執行中，如果沒有被執行，或作業的執行完全被終止，則添加，否則跳過
				boolean isRun = ReflectInterface.ckeckRunJob(jobsAction.setJobInfo().getJobName(),
						jobsAction.setJobInfo().getJobGroup());
				if (isRun) {
					continue;
				}
				JobDetail timeJob = newJob(jobClass)
						.withIdentity(jobsAction.setJobInfo().getJobName(), jobsAction.setJobInfo().getJobGroup()).build();
				Trigger jobTrigger = null;
				if (jobsAction.setJobInfo().getTriggerType() == null) {
					LogAction.getInstance().debug(
							"請確認TriggerType是否為有效值，" + jobsAction.setJobInfo().getTriggerType() + "，發生Job為：" + job.getName());
					continue;
				} else if (jobsAction.setJobInfo().getTriggerType() == 1) {// 每分鐘一次
					jobTrigger = Tool.getMinutelyTrigger(jobsAction.setJobInfo().getStartTime());

				} else if (jobsAction.setJobInfo().getTriggerType() == 2) {// 每小時一次
					jobTrigger = Tool.getHourlyTrigger(jobsAction.setJobInfo().getStartTime());

				} else if (jobsAction.setJobInfo().getTriggerType() == 3) {// 每天一次
					jobTrigger = Tool.getDaylyTrigger(jobsAction.setJobInfo().getStartTime());

				} else if (jobsAction.setJobInfo().getTriggerType() == 0 && jobsAction.setJobInfo().getCron() != null) {// corn表達式
					try {
						jobTrigger = Tool.getCronTrigger(jobsAction.setJobInfo().getStartTime(), jobsAction.setJobInfo().getCron(),
								jobsAction.setJobInfo().getCronDescription());
					} catch (Exception ex) {
						LogAction.getInstance().debug("請確認Cron是否為有效值，" + jobsAction.setJobInfo().getCron() + "，發生Job為：" + job.getName());
					}
				} else {
					LogAction.getInstance().debug(
							"請確認TriggerType是否為有效值，" + jobsAction.setJobInfo().getTriggerType() + "，發生Job為：" + job.getName());
					continue;
				}

				if (timeJob != null && jobTrigger != null) {
					scheduler.scheduleJob(timeJob, jobTrigger);
				}

			}

			scheduler.start();

		} catch (Exception ex) {
			LogAction.getInstance().debug(ex.getMessage());
		}

	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
	
	protected void initial() throws SiDCException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
	}
}