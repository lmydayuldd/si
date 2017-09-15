package com.sidc.sdk.blackcore.rcumodesetting;

import java.util.List;

import com.derex.cm.stb.api.request.APIRequest;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeResponse;
import com.sidc.sdk.abs.AbsHttpClient;

public class RcuModeListClient extends AbsHttpClient<List<RcuModeResponse>> {

	public RcuModeListClient(String host) {
		super(host, "/rcu/listmode");
	}

	@Override
	public String request() throws Exception {
		APIRequest request = new APIRequest(null);
		return super.getGson().toJson(request);
	}

	@Override
	public List<RcuModeResponse> response(String json) throws Exception {
		if (json == null || json.length() == 0) {
			return null;
		}

		List<RcuModeResponse> list = super.getGson().fromJson(json, new TypeToken<List<RcuModeResponse>>() {
		}.getType());

		return list;

		// return null;
	}

}
