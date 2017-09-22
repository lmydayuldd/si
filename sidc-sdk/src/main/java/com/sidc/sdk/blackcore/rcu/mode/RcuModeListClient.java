package com.sidc.sdk.blackcore.rcu.mode;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.sdk.abs.AbsHttpClient;

public class RcuModeListClient extends AbsHttpClient<String> {

	public RcuModeListClient(String host) {
		super(host, "/rcu/listmode");
	}

	@Override
	public String request() throws Exception {
		APIRequest request = new APIRequest(null);
		return super.getGson().toJson(request);
	}

	@Override
	public String response(String json) throws Exception {
		if (json == null || json.length() == 0) {
			return null;
		}
		return json;
	}

}
