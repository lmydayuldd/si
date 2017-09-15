package com.sidc.quartz.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.corejob.common.ReflectInterface;
import com.sidc.quartz.api.request.ScheduleListRequest;
import com.sidc.quartz.api.response.ScheduleStateResponse;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class QueryJob extends AbstractAPIProcess {

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		List<ScheduleStateResponse> list = new ArrayList<ScheduleStateResponse>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

					String nextFireTime = format.format(trigger.getNextFireTime());
					String during = ReflectInterface
							.formatDuring(trigger.getNextFireTime().getTime() - new Date().getTime());
					if (trigger.getNextFireTime() == null || !triggerState.name().equals("NORMAL")) {
						nextFireTime = "-";
						during = "-";
					}

					String preFireTime = "-";
					if (trigger.getPreviousFireTime() != null) {
						preFireTime = format.format(trigger.getPreviousFireTime());
					}

					ScheduleStateResponse enity = new ScheduleStateResponse(jobKey.getName(), jobKey.getGroup(),
							nextFireTime, preFireTime, during, trigger.getDescription(),
							format.format(trigger.getStartTime()), triggerState.name().toString());
					list.add(enity);
				}
			}
		} catch (Exception e) {
			LogAction.getInstance().error("Query Job Error:", e);
		}
		return new ScheduleListRequest(list);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

}
