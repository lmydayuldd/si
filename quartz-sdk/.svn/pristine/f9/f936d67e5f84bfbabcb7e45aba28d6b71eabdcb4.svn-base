package com.sidc.quartz.sdk.rcu;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.quartz.api.request.rcu.RcuScheduleCheckOutUpdateRequest;
import com.sidc.quartz.sdk.abs.AbsHttpClient;

public class RcuScheduleCheckOutUpdateClient<T> extends AbsHttpClient<T>{

	private RcuScheduleCheckOutUpdateRequest enity;
	
	public RcuScheduleCheckOutUpdateClient(String host, RcuScheduleCheckOutUpdateRequest enity) {
		super(host, "/schedule/rcu/checkoutupdate");
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
		return null;
	}

}
