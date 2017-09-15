package com.sidc.ra.logical.api.rcuhvaccentercontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.ra.rcuhvaccentercontrol.request.HvacControlRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.rcu.connector.bean.command.AirConditionCommander;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.sdk.engine.RCUCommandClient;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class HvacControlProcess extends AbstractAPIProcess {
	private final HvacControlRequest entity;
	private final static String STEP = "3";

	public HvacControlProcess(final HvacControlRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		List<String> roomList = new ArrayList<String>();

		switch (entity.getType()) {
		case "all":
			roomList = RoomManager.getInstance().listRoom();
			break;
		case "check-out":
			roomList = RoomManager.getInstance().listIdleRoom();
			break;
		case "check-in":
			roomList = RoomManager.getInstance().listRoomWithCheckIn();
			break;
		case "floor":
			roomList = RoomManager.getInstance().listRoomByFloor(entity.getItemlist());
			break;
		case "room":
			roomList = entity.getItemlist();
			break;
		default:
			break;
		}
		LogAction.getInstance().debug("step 1/" + STEP + " :get room list success.");

		AirConditionCommander hvacCommander = new AirConditionCommander((byte) 8);
		hvacCommander.setPower(true);
		hvacCommander.setFunction(entity.getFunction());
		hvacCommander.setTemperature(entity.getTemperature());
		hvacCommander.setTimer(entity.getDelayclosetime());
		hvacCommander.setSpeed(0);
		LogAction.getInstance().debug("step 2/" + STEP + " :format AirConditionCommander success." + hvacCommander);

		for (final String roomNo : roomList) {
			final RCUCommander command = new RCUCommander(UUID.randomUUID().toString(), roomNo, "HVAC-ALL",
					hvacCommander);
			new RCUCommandClient<Object>(configure(SidcUrlName.RCUENGINE.toString()), command).execute();
		}
		LogAction.getInstance().debug("step 3/" + STEP + " :send command success.");

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
		if (StringUtils.isBlank(entity.getType())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(type).");
		}
		if (entity.getDelayclosetime() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(delay close time).");
		}
		if (entity.getType().equals("room") || entity.getType().equals("floor")) {
			if (entity.getItemlist().isEmpty()) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(item list).");
			}
			if (entity.getType().equals("room") && !RoomManager.getInstance().isExist(entity.getItemlist())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(not find room no).");
			}
		}
	}

	private String configure(final String serverName) throws SiDCException {
		final SidcUrlsConfiguration sidcConfigure = (SidcUrlsConfiguration) DataCenter.getInstance()
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
