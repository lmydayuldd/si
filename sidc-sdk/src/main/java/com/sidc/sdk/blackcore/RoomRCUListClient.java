/**
 * 
 */
package com.sidc.sdk.blackcore;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.sits.room.response.RoomNoListResponse;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 *
 */
public class RoomRCUListClient extends AbsHttpClient<RoomNoListResponse> {

	public RoomRCUListClient(String host) {
		super(host, "/listroomrcu");
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(null);

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
