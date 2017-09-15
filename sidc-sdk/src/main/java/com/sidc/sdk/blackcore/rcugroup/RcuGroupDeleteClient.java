package com.sidc.sdk.blackcore.rcugroup;

import com.sidc.sdk.abs.AbsHttpClient;

public class RcuGroupDeleteClient<T> extends AbsHttpClient<T> {
	private String request;

	public RcuGroupDeleteClient(final String host, final String request) {
		super(host, "/rcu/group/delete");
		this.request = request;
	}

	@Override
	protected String request() throws Exception {
		return this.request;
	}

	@Override
	protected String response(final String json) throws Exception {
		if (json == null || json.length() == 0) {
			return null;
		}
		return json;
	}

}
