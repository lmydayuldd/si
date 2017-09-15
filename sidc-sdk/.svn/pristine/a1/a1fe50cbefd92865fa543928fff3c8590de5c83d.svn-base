package com.sidc.sdk.blackcore.rcu.command;

import com.sidc.sdk.abs.AbsHttpClient;

public class RcuHvacCommandClient<T> extends AbsHttpClient<T> {
	private String request;

	public RcuHvacCommandClient(String host, String request) {
		super(host, "/rcu/hvaccommand");
		this.request = request;
	}

	@Override
	protected String request() throws Exception {
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	protected Object response(String json) throws Exception {
		// TODO Auto-generated method stub
		if (json == null || json.length() == 0) {
			return null;
		}
		return null;
	}
}
