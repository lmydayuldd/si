/**
 * 
 */
package com.sidc.sdk.blackcore;

import java.util.List;

import com.derex.cm.stb.api.request.APIRequest;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.response.RoomInfoEnity;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 *
 */
public class RoomRCUInfoClient extends AbsHttpClient<RoomInfoEnity> {

	public RoomRCUInfoClient(String host) {
		super(host, "/rcu/roominfo");
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(null);

		return super.getGson().toJson(request);
	}

	@Override
	public List<RoomInfoEnity> response(String json) throws Exception {

		if (json == null || json.length() == 0) {
			return null;
		}

		List<RoomInfoEnity> enity = super.getGson().fromJson(json, new TypeToken<List<RoomInfoEnity>>() {
		}.getType());

		return enity;
	}

}
