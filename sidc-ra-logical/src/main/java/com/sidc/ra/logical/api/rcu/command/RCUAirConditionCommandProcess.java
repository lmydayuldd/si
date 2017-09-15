package com.sidc.ra.logical.api.rcu.command;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.rcu.request.HvacCommandRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.rcu.connector.bean.command.AirConditionCommander;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.sdk.engine.RCUCommandClient;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class RCUAirConditionCommandProcess extends AbstractAPIProcess {

	private HvacCommandRequest enity;
	
	public RCUAirConditionCommandProcess(HvacCommandRequest enity) {
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

		AirConditionCommander hvacCommander = new AirConditionCommander((byte) 8);
		hvacCommander.setPower(this.enity.isPower());
		hvacCommander.setFunction(this.enity.getFunction());
		hvacCommander.setTemperature(this.enity.getTemperature());
		hvacCommander.setSpeed(this.enity.getSpeed());
		hvacCommander.setTimer(1);

		final RCUCommander command = new RCUCommander(UUID.randomUUID().toString(), this.enity.getRoomno(), "HVAC-ALL",
				hvacCommander);

		return new RCUCommandClient<Object>(configure(SidcUrlName.RCUENGINE.toString()), command).execute();
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Rquest is illegal");
		}

		if (StringUtils.isBlank(this.enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "RoomNo is empty");
		}

		if (StringUtils.isBlank(this.enity.getIp())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "STB IP is empty");
		}
	}

	public String configure(final String serverName) throws SiDCException {
		SidcUrlsConfiguration sidcConfigure = (SidcUrlsConfiguration) DataCenter.getInstance()
				.get(SidcUrlName.SITS.toString());
		List<SidcUrlsLink> urlsLinks = sidcConfigure.getUrls();
		String url = null;
		for (SidcUrlsLink sidcUrlsLink : urlsLinks) {
			if (sidcUrlsLink.getName().equalsIgnoreCase(serverName))
				url = sidcUrlsLink.getUrl();
		}
		return url;
	}
}
