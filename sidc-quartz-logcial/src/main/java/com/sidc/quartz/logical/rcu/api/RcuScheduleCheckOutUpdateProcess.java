package com.sidc.quartz.logical.rcu.api;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.conf.Env;
import com.sidc.quartz.api.command.key.QuartzCommand;
import com.sidc.quartz.api.request.ScheduleCommandRequest;
import com.sidc.quartz.api.request.rcu.RcuScheduleCheckOutUpdateRequest;
import com.sidc.quartz.sdk.ScheduleUpdateClient;
import com.sidc.quartz.sdk.command.ScheduleCommandClient;
import com.sidc.rcu.connector.bean.command.AirConditionCommander;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuScheduleCheckOutUpdateProcess extends AbstractAPIProcess {

	private SidcUrlsConfiguration sidcConfigure = null;
	private final RcuScheduleCheckOutUpdateRequest entity;
	private final static String STEP = "7";

	public RcuScheduleCheckOutUpdateProcess(final RcuScheduleCheckOutUpdateRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		sidcConfigure = readSidcUrlsConfiguration(new File(Env.SYSTEM_DEF_PATH + Env.SIDC_URL_PATH));
		final String time[] = entity.getStarttime().split(":");

		// second,minute,hour,day,month,year
		final String cronArr[] = { "*", time[1], time[0], "*", "*", "?" };

		String cronStr = "";
		for (int i = 0; i < cronArr.length; i++) {
			cronStr += cronArr[i] + " ";
		}
		LogAction.getInstance().debug("step 1/" + STEP + ":format cron cron success.");

		AirConditionCommander hvacCommander = new AirConditionCommander((byte) 8);
		hvacCommander.setPower(true);
		hvacCommander.setFunction(entity.getFunction());
		hvacCommander.setTemperature(entity.getTemperature());
		hvacCommander.setTimer(entity.getDelayclosetime());
		hvacCommander.setSpeed(0);
		LogAction.getInstance().debug("step 2/" + STEP + ":format request success." + hvacCommander);

		final int status = entity.isOpenswitch() == true ? 1 : 0;

		final String jobName = QuartzJobList.CheckOutTimer.toString();

		final ScheduleInfoRequest infoRequest = new ScheduleInfoRequest(jobName);
		final ScheduleInfoResponse infoResponse = new ScheduleInfoClient<Object>(sidcConfigure.getUrls().get(1).toString(), infoRequest)
				.execute();
		if (infoResponse == null) {
			throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find job :" + jobName + ".");
		}

		final ScheduleUpdateInfoRequest request = new ScheduleUpdateInfoRequest(jobName, status,
				this.entity.getStarttime(), "Close HVAC at Check out room schedule", cronStr);
		new ScheduleUpdateClient<Object>(sidcConfigure.getUrls().get(6).toString(), request).execute();
		LogAction.getInstance()
				.debug("step 3/" + STEP + ":update success.(RcuScheduleManager|updateCheckOutExecutionTime)");

		ScheduleCommandRequest commandRequest = new ScheduleCommandRequest(QuartzCommand.RESCHEDULE.toString(),
				jobName, infoResponse.getGroupname(), cronStr, infoResponse.getDescription());
		LogAction.getInstance().debug("step 4/" + STEP + ":QuartzCommand." + commandRequest);

		new ScheduleCommandClient(sidcConfigure.getUrls().get(1).toString(), commandRequest).execute();
		LogAction.getInstance().debug("step 5/" + STEP + ":update online quartz.");

		if (entity.isOpenswitch()) {
			commandRequest = new ScheduleCommandRequest(QuartzCommand.RESUME.toString(), jobName,
					infoResponse.getGroupname());
		} else {
			commandRequest = new ScheduleCommandRequest(QuartzCommand.PAUSE.toString(), jobName,
					infoResponse.getGroupname());
		}
		LogAction.getInstance().debug("step 6/" + STEP + ":QuartzCommand." + commandRequest);

		new ScheduleCommandClient(sidcConfigure.getUrls().get(6).toString(), commandRequest).execute();
		LogAction.getInstance().debug("step 7/" + STEP + ":Quartz job status update.");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is null.");
		}
		if (entity.getFunction() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(function).");
		}
		if (entity.getTemperature() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(temperature).");
		}
		if (StringUtils.isBlank(entity.getStarttime())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(start time).");
		}
		if (entity.getDelayclosetime() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(delay close time).");
		}
	}

	private SidcUrlsConfiguration readSidcUrlsConfiguration(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(SidcUrlsConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		SidcUrlsConfiguration config = (SidcUrlsConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;
	}
}
