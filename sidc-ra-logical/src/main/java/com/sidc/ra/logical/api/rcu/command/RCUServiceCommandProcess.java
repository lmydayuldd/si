package com.sidc.ra.logical.api.rcu.command;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.command.bean.RcuCommanderBean;
import com.sidc.blackcore.api.ra.command.request.MobileCommanderRequeset;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.ra.logical.abs.AbstractAuthAPIProcess;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.bean.command.ServiceCommander;
import com.sidc.rcu.sdk.engine.RCUCommandClient;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RCUServiceCommandProcess extends AbstractAuthAPIProcess {
	private MobileCommanderRequeset entity;
	private RcuCommanderBean commandEntity;

	public RCUServiceCommandProcess(final MobileCommanderRequeset entity, final String ip) {
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno(), ip);
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + this.entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final ServiceCommander serviceCommander = new ServiceCommander(
				(int) Double.parseDouble(commandEntity.getValue()));
		LogAction.getInstance().debug("step 2/3 serviceCommander entity:" + serviceCommander);

		final RCUCommander command = new RCUCommander(entity.getUuid(), entity.getRoomno(), entity.getKeycode(),
				serviceCommander);
		LogAction.getInstance().debug("step 3/3 RCUCommander entity:" + command);

		return new RCUCommandClient<Object>(configure(SidcUrlName.RCUENGINE.toString()), command).execute();
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Rquest is illegal.");
		}
		if (StringUtils.isBlank(this.entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "RoomNo is empty.");
		}
		if (StringUtils.isBlank(this.entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Public key is empty.");
		}
		if (StringUtils.isBlank(this.entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Private key is empty.");
		}
		if (StringUtils.isBlank(this.entity.getKeycode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "keycode is empty.");
		}
		final Gson gson = new Gson();
		commandEntity = (RcuCommanderBean) gson.fromJson(gson.toJson(entity.getData()), RcuCommanderBean.class);
		LogAction.getInstance().debug("step 1/3 data entity:" + commandEntity);

		if (commandEntity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "commandEntity is empty.");
		}
		if (StringUtils.isBlank(commandEntity.getValue())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "value is empty.");
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
