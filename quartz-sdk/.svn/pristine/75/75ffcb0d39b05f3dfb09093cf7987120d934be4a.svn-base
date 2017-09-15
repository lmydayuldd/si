package com.sidc.quartz.sdk;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.quartz.api.request.ScheduleUpdateInfoRequest;
import com.sidc.quartz.sdk.abs.AbsHttpClient;

public class ScheduleUpdateClient<T> extends AbsHttpClient<T> {
	
	private ScheduleUpdateInfoRequest enity;

	public ScheduleUpdateClient(String host, ScheduleUpdateInfoRequest enity) {
		super(host, "/schedule/update");
		this.enity = enity;
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
		return null;
	}
}
