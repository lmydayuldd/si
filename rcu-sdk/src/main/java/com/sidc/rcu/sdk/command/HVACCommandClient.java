package com.sidc.rcu.sdk.command;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.sits.rcu.request.HvacCommandRequest;
import com.sidc.rcu.sdk.abs.AbsHttpClient;

public class HVACCommandClient extends AbsHttpClient<String> {

	private HvacCommandRequest enity;
	
	public HVACCommandClient(String host, HvacCommandRequest enity) {
		super(host, "/rcuhvac");
		this.enity = enity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String request() throws Exception {
		// TODO Auto-generated method stub
		APIRequest request = new APIRequest(this.enity);

		return super.getGson().toJson(request);
	}

	@Override
	protected Object response(String json) throws Exception {
		// TODO Auto-generated method stub
		if (json == null || json.length() == 0) {
			return null;
		}

		return json;
	}

}
