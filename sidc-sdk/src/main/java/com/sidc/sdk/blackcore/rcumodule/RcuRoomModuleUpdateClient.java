package com.sidc.sdk.blackcore.rcumodule;

import com.sidc.sdk.abs.AbsHttpClient;

public class RcuRoomModuleUpdateClient<T> extends AbsHttpClient<T> {
	private String request;

	public RcuRoomModuleUpdateClient(String host, String request) {
		super(host, "/rcu/roommodule/update");
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
