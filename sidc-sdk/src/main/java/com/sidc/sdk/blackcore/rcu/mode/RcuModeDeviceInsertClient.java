package com.sidc.sdk.blackcore.rcu.mode;

import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeDeviceResponse;
import com.sidc.sdk.abs.AbsHttpClient;

public class RcuModeDeviceInsertClient<T> extends AbsHttpClient<T> {
	private String request;

	public RcuModeDeviceInsertClient(final String host, final String request) {
		super(host, "/rcu/insertmodedevice");
		this.request = request;
	}

	@Override
	protected String request() throws Exception {
		return this.request;
	}

	@Override
	protected List<RcuModeDeviceResponse> response(final String json) throws Exception {
		if (json == null || json.length() == 0) {
			return null;
		}

		return null;
	}

}
