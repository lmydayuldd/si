package com.sidc.sits.logical.tvchannel;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.tvchannel.request.TvChannelChangeRequest;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.dao.sits.manager.StbListManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class TvChannelChangeProcess extends AbstractAuthAPIProcess {
	private final TvChannelChangeRequest entity;

	public TvChannelChangeProcess(final TvChannelChangeRequest entity) {
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno());
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
		final List<String> stbipList = StbListManager.getInstance().listStbIp(entity.getRoomno());
		LogAction.getInstance().debug("step 1/2 get stb ip success.");

		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
		HttpClientUtils.sendPostWithTvChannelChange(sitsUrl + PageList.STB_TV_CHANNEL_CHANGE, stbipList,
				entity.getChannelno());
		LogAction.getInstance()
				.debug("step 2/2 send post TvChannelChange success(url:" + PageList.STB_TV_CHANNEL_CHANGE + ").");

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(private key).");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(public key).");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(room no).");
		}
		if (StringUtils.isBlank(entity.getIp())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(ip).");
		}
		if (StringUtils.isBlank(entity.getChannelno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(channl no).");
		}
		if (!RoomManager.getInstance().isCheckin(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room not check in.");
		}
	}

}
