package com.sidc.rcu.hmi.logical.schedule;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.request.schedule.ScheduleInfoBean;
import com.sidc.rcu.hmi.request.schedule.ScheduleInfoDescriptionBean;
import com.sidc.rcu.hmi.request.schedule.ScheduleInfoRequest;
import com.sidc.rcu.hmi.request.schedule.ScheduleInfoResponse;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class ScheduleCheckOutInfoProcess extends AbstractAPIProcess {
	private String STEP = "2";

	public ScheduleCheckOutInfoProcess() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final ScheduleInfoRequest request = new ScheduleInfoRequest("CheckOutTimer");
/*
		final String response = new ScheduleInfoClient(blackcoreSetting.getUrl(),
				UDPParser.getInstance().toJsonByContent(request)).execute();

		LogAction.getInstance().debug("step 1/" + STEP + ":ScheduleInfoClient response=" + response);

		ScheduleInfoDescriptionBean descriptionEntity = new ScheduleInfoDescriptionBean(false, 1, 24, 0);
		String[] executionTime = { "", "30", "9" };

		final ScheduleInfoBean infoEntity = (ScheduleInfoBean) UDPParser.getInstance().parse(response,
				ScheduleInfoBean.class);

		if (infoEntity != null) {
			descriptionEntity = (ScheduleInfoDescriptionBean) UDPParser.getInstance().parse(infoEntity.getDescription(),
					ScheduleInfoDescriptionBean.class);

			descriptionEntity.setTimer(descriptionEntity.getTimer() / 60);

			if (!StringUtils.isBlank(infoEntity.getExecutiontime())) {
				String[] timeCrons = infoEntity.getExecutiontime().split(" ");

				if (timeCrons.length >= 3) {
					if (StringUtils.isNumeric(timeCrons[1]) && StringUtils.isNumeric(timeCrons[2])) {
						executionTime = timeCrons;
					}
				}
			}
		}
		LogAction.getInstance().debug("step 2/" + STEP + ":response to entity success.");

		return new ScheduleInfoResponse(infoEntity.getStatus(), executionTime[2] + ":" + executionTime[1],
				descriptionEntity);
				*/
		
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}
}
