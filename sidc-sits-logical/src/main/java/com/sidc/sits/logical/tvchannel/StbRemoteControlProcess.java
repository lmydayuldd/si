package com.sidc.sits.logical.tvchannel;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;

import com.derex.cm.stb.api.request.APIRequest;
import com.derex.cm.stb.api.request.StbRemoteControlRequest;
import com.google.gson.Gson;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class StbRemoteControlProcess extends AbstractAuthAPIProcess {

	private StbRemoteControlRequest enity;

	public StbRemoteControlProcess(final StbRemoteControlRequest enity) {
		super(enity.getPublickey(), enity.getPrivatekey(), enity.getRoomno());
		this.enity = enity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		
		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
		
		final APIRequest request = new APIRequest(enity);
		final Gson gson = new Gson();
		final String json = gson.toJson(request);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");
		HttpClientUtils.httpSendPost(sitsUrl + PageList.STB_REMOTE_CONTROL, strEntity);
		
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room no is empty.");
		}
		if (StringUtils.isBlank(enity.getStbip())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "STB IP is empty.");
		}
		if (StringUtils.isBlank(enity.getFunction())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "remote function is empty.");
		}
		if (StringUtils.isBlank(enity.getAction())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "remote action is empty.");
		}
	}

}
