/**
 * 
 */
package com.sidc.sdk.blackcore.rcumodesetting;

import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.sdk.abs.AbsHttpClient;

public class RcuDeviceListByGroupClient extends AbsHttpClient<List<RcuDeviceEnity>> {
	private String request;

	public RcuDeviceListByGroupClient(final String host, final String request) {
		super(host, "/rcu/listdevicebygroup");
		this.request = request;
	}

	@Override
	public String request() throws Exception {
		return this.request;
	}

	@Override
	public String response(String json) throws Exception {

		if (json == null || json.length() == 0) {
			return null;
		}
		//
		// List<RcuDeviceEnity> enity = super.getGson().fromJson(json, new
		// TypeToken<List<RcuDeviceEnity>>() {
		// }.getType());

		return json;
	}

}
