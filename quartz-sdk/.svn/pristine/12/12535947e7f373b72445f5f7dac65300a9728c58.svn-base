/**
 * 
 */
package com.sidc.quartz.sdk;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.quartz.api.request.ScheduleInfoRequest;
import com.sidc.quartz.sdk.abs.AbsHttpClient;

public class ScheduleInfoClient<T> extends AbsHttpClient<T> {
	
	private ScheduleInfoRequest enity;

	public ScheduleInfoClient(final String host, final ScheduleInfoRequest enity) {
		super(host, "/schedule/info");
		this.enity = enity;
	}

	@Override
	public String request() throws Exception {
		APIRequest request = new APIRequest(this.enity);
		
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
