package com.sidc.ra.logical.api.rcu.command;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.rcu.request.ServiceCommandRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.bean.command.ServiceCommander;
import com.sidc.rcu.sdk.engine.RCUCommandClient;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RCUServicceProcess extends AbstractAPIProcess {

	private ServiceCommandRequest enity;

	public RCUServicceProcess(ServiceCommandRequest enity) {
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
		
		final ServiceCommander serviceCommander = new ServiceCommander(Integer.parseInt(enity.getValue()));
		final RCUCommander command = new RCUCommander(UUID.randomUUID().toString(), enity.getRoomno(),
				enity.getKeycode(), serviceCommander);

		return new RCUCommandClient<Object>(configure(SidcUrlName.RCUENGINE.toString()), command).execute();
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}

		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Room No is empty");
		}

		if (StringUtils.isBlank(enity.getKeycode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Keycode is empty");
		}

		if (StringUtils.isBlank(enity.getValue())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Value is empty");
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
