package com.sidc.sdk.blackcore.rcumodesetting;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeDeviceResponse;
import com.sidc.sdk.abs.AbsHttpClient;

public class RcuModeDeviceListClient extends AbsHttpClient<List<RcuModeDeviceResponse>> {
	private String request;

	public RcuModeDeviceListClient(String host, String request) {
		super(host, "/rcu/listmodedevice");
		this.request = request;
	}

	@Override
	protected String request() throws Exception {
		return request;
	}

	@Override
	protected List<RcuModeDeviceResponse> response(String json) throws Exception {
		if (json == null || json.length() == 0) {
			return null;
		}

		List<RcuModeDeviceResponse> enity = super.getGson().fromJson(json,
				new TypeToken<List<RcuModeDeviceResponse>>() {
				}.getType());

		return enity;
	}

}
