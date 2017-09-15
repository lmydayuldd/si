/**
 * 
 */
package com.sidc.sdk.agent;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 *
 */
public class CheckinClient extends AbsHttpClient {

	private CheckInRequest enity;

	public CheckinClient(String host, CheckInRequest enity) {
		super(host, "/checkin");
		this.enity = enity;
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(enity);

		return super.getGson().toJson(request);
	}

	@Override
	public Object response(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
