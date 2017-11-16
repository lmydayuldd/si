package com.sidc.sdk.blackcore.rcu.sosrecord;

import com.sidc.sdk.abs.AbsHttpClient;

public class SosRecordClient<T> extends AbsHttpClient<T> {
	private String request;

	public SosRecordClient(final String host, final String request) {
		super(host, "/rcu/sosrecord");
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
