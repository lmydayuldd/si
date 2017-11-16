package com.sidc.sdk.blackcore.rcumodule;

import com.sidc.sdk.abs.AbsHttpClient;

public class RcuModuleDeviceListClient<T> extends AbsHttpClient<T> {
	private String request;

	public RcuModuleDeviceListClient(String host, String request) {
		super(host, "/rcu/listmoduledevice");
		this.request = request;
	}

	@Override
	protected String request() throws Exception {
		return request;
	}

	@Override
	protected String response(final String json) throws Exception {
		if (json == null || json.length() == 0) {
			return null;
		}
		return json;
	}

}
