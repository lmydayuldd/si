package com.sidc.ra.logical.api.rcuhvaccentercontrol;

import java.util.List;
import java.util.UUID;

import com.sidc.blackcore.api.ra.rcuhvaccentercontrol.request.HvacCenterControlRequest;
import com.sidc.blackcore.api.ra.response.RoomInfoEnity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.ra.manager.RoomRCUManager;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.sdk.engine.RCUCommandClient;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class HvacCenterControlProcess extends AbstractAPIProcess {
	private final HvacCenterControlRequest entity;

	public HvacCenterControlProcess(final HvacCenterControlRequest entity) {
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

		final String url = configure(SidcUrlName.RCUENGINE.toString());
		LogAction.getInstance().debug("step 1/3: get blackcore url success. " + url);

		final Object data = "{\"isPower\": true,\"function\":" + entity.getFunction() + ",\"temperature\": "
				+ entity.getTemperature() + "}";

		final String keycode = "HVAC-ALL";

		List<RoomInfoEnity> result = RoomRCUManager.getInstance().listRoomRcuInfo();
		LogAction.getInstance().debug("step 2/3: get room list success.");

		for (final RoomInfoEnity entity : result) {
//			if (!entity.isCheckin())
//				continue;

			final RCUCommander commandEntity = new RCUCommander(UUID.randomUUID().toString(), entity.getRoomno(),
					keycode, data);
			new RCUCommandClient<Object>(url, commandEntity).execute();
		}
		LogAction.getInstance().debug("step 3/3: send command success.");

		return null;
	} 

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request is illegal.");
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
