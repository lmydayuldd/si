/**
 * 
 */
package com.sidc.sdk.blackcore.rcumodesetting;

import java.util.List;

import com.derex.cm.stb.api.request.APIRequest;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.response.RcuAgentBehaviorEntity;
import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.sdk.abs.AbsHttpClient;

public class RcuAgentBehaviorListClient extends AbsHttpClient<List<RcuAgentBehaviorEntity>> {

	public RcuAgentBehaviorListClient(String host) {
		super(host, "/rcu/agentbehaviorlist");
	}

	@Override
	public String request() throws Exception {
		APIRequest request = new APIRequest(null);
		return super.getGson().toJson(request);
	}

	@Override
	public List<RcuAgentBehaviorEntity> response(String json) throws Exception {

		if (json == null || json.length() == 0) {
			return null;
		}

		List<RcuAgentBehaviorEntity> enity = super.getGson().fromJson(json,
				new TypeToken<List<RcuAgentBehaviorEntity>>() {
				}.getType());

		return enity;
	}

}
