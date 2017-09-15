/**
 * 
 */
package com.sidc.sdk.blackcore.rcugroup;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupNewRoomEnity;
import com.sidc.blackcore.api.ra.request.RoomModuleRequest;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 * @param <T>
 *
 */
public class RoomNewRcuGroupClient<T> extends AbsHttpClient<T> {

	private RcuGroupNewRoomEnity request;

	public RoomNewRcuGroupClient(String host, RcuGroupNewRoomEnity request) {
		super(host, "/rcu/buildroomrcugroup");
		this.request = request;
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(this.request);

		return super.getGson().toJson(request);
	}

	@Override
	public Object response(String json) throws Exception {
		return null;
	}

}
