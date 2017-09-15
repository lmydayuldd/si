package com.sidc.sdk.blackcore.rcumodesetting;

import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuAgentBehaviorEntity;
import com.sidc.sdk.abs.AbsHttpClient;

public class RcuDeviceListWithModeClient extends AbsHttpClient<List<RcuAgentBehaviorEntity>> {
	private String request;

	public RcuDeviceListWithModeClient(final String host, final String request) {
		super(host, "/rcu/listmodesetting");
		this.request = request;
	}

	@Override
	public String request() throws Exception {
		return this.request;
	}

	@Override
	public String response(final String json) throws Exception {
		if (json == null || json.length() == 0) {
			return null;
		}

		return json;
	}

}
