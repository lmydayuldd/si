/**
 * 
 */
package com.sidc.quartz.sdk;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.quartz.api.request.ScheduleDataRequest;
import com.sidc.quartz.sdk.abs.AbsHttpClient;

public class ScheduleDataClient<T> extends AbsHttpClient<T> {
	
	private ScheduleDataRequest enity;

	public ScheduleDataClient(final String host, final ScheduleDataRequest enity) {
		super(host, "/schedule/data");
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
