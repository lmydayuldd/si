package com.sidc.quartz.sdk;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.quartz.api.request.ScheduleStatusRequest;
import com.sidc.quartz.sdk.abs.AbsHttpClient;

public class ScheduleStatusClient<T> extends AbsHttpClient<T> {

	private ScheduleStatusRequest enity;
	
	public ScheduleStatusClient(String host, ScheduleStatusRequest enity) {
		super(host, "/schedule/status");
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
