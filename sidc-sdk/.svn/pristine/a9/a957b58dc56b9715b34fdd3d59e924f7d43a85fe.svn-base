package com.sidc.sdk.blackcore.rcumodesetting;

import com.sidc.sdk.abs.AbsHttpClient;

public class RcuDeviceSettingInsertClient<T> extends AbsHttpClient<T> {
	private String request;

	public RcuDeviceSettingInsertClient(final String host, final String request) {
		super(host, "/rcu/insertmodesetting");
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
