package com.sidc.sdk.blackcore.rcu.mode;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * 
 * @author Allen Huang
 *
 * @param <T>
 */
public class RcuModeClient<T> extends AbsHttpClient<T> {

	private RcuRoomMode request;
	public RcuModeClient(String host, RcuRoomMode request) {
		super(host, "/rcumode");
		this.request = request;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String request() throws Exception {
		// TODO Auto-generated method stub
		APIRequest request = new APIRequest(this.request);

		return super.getGson().toJson(request);
	}

	@Override
	protected Object response(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
