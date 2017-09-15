/**
 * 
 */
package com.sidc.sdk.blackcore.rcu.room;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.ra.request.DynamicRoomRequest;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 *
 */
public class DynamicRoomGroupInfoClient extends AbsHttpClient<String> {

	private DynamicRoomRequest enity;

	public DynamicRoomGroupInfoClient(String host, DynamicRoomRequest enity) {
		super(host, "/rcu/dynamicrcu");
		this.enity = enity;
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(enity);

		return super.getGson().toJson(request);
	}

	@Override
	public String response(String json) throws Exception {
		return json;
	}

}
