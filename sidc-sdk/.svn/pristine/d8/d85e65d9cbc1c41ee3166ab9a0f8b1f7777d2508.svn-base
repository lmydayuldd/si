package com.sidc.sdk.blackcore.rcu.command;

import com.sidc.sdk.abs.AbsHttpClient;

public class RcuCommandClient<T> extends AbsHttpClient<T> {
	private String request;

	public RcuCommandClient(final String host, final String url, final String request) {
		super(host, url);
		this.request = request;
	}

	@Override
	protected String request() throws Exception {
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	protected Object response(final String json) throws Exception {
		// TODO Auto-generated method stub
		if (json == null || json.length() == 0) {
			return null;
		}

		return json;
	}
}
