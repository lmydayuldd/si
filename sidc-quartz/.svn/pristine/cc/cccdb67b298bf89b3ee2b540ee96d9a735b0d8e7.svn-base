package com.sidc.corejob.common;

import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * 
 * @author Allen Huang
 *
 */
public class Tool {

	@SuppressWarnings("static-access")
	public static Trigger getMinutelyTrigger(Date startTime) {
		int randomNum = (int) (Math.random() * 100); // 100以內的隨機數
		if (startTime == null) {
			startTime = new Date();
		}
		Trigger trigger = newTrigger()
				.withIdentity("Minutely_" + startTime.getTime() + randomNum, "MinutelyTriggerGroup").startAt(startTime)
				.withDescription("每分鐘執行一次")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatMinutelyForever(1)).build();
		return trigger;
	}

	@SuppressWarnings("static-access")
	public static Trigger getHourlyTrigger(Date startTime) {
		int randomNum = (int) (Math.random() * 100); // 100以內的隨機數
		if (startTime == null) {
			startTime = new Date();
		}
		Trigger trigger = newTrigger().withIdentity("Hourly_" + startTime.getTime() + randomNum, "HourlyTriggerGroup")
				.startAt(startTime).withDescription("每小時執行一次")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatHourlyForever(1)).build();
		return trigger;
	}

	@SuppressWarnings("static-access")
	public static Trigger getDaylyTrigger(Date startTime) {
		int randomNum = (int) (Math.random() * 100); // 以內的隨機數
		if (startTime == null) {
			startTime = new Date();
		}
		Trigger trigger = newTrigger().withIdentity("Dayly_" + startTime.getTime() + randomNum, "DaylyTriggerGroup")
				.startAt(startTime).withDescription("每天執行一次")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatHourlyForever(24)).build();
		return trigger;
	}

	@SuppressWarnings({ "static-access", "unused" })
	public static Trigger getCronTrigger(Date startTime, String cronString, String cronDescription) {
		if (startTime == null) {
			startTime = new Date();
		}
		CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
				.withIdentity("CronTrigger_" + String.valueOf(java.lang.Math.random()).split("\\.")[1],
						"CronTriggerGroup")
				.startAt(startTime)
				.withDescription(cronDescription)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
		return trigger;
	}
	
}
