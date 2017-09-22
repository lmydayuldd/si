/**
 * 
 */
package com.sidc.sdk.blackcore.rcu.room;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.sdk.abs.AbsHttpClient;

public class RoomStatusListClient extends AbsHttpClient<String> {

	public RoomStatusListClient(String host) {
		super(host, "/listroomstatus");
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(null);

		return super.getGson().toJson(request);
	}

	@Override
	public String response(String json) throws Exception {
		return json;
	}

}
