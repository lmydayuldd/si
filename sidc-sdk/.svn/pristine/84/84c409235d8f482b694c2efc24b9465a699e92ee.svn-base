/**
 * 
 */
package com.sidc.sdk.sits;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.sits.room.response.RoomNoListResponse;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 *
 */
public class RoomNoListClient extends AbsHttpClient<RoomNoListResponse> {

	public RoomNoListClient(String host) {
		super(host, "/roomnolist");
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(null);

		return super.getGson().toJson(request);
	}

	@Override
	public RoomNoListResponse response(String json) throws Exception {

		if (json == null || json.length() == 0) {
			return null;
		}

		return super.getGson().fromJson(json, RoomNoListResponse.class);
	}

}
