/**
 * 
 */
package com.sidc.sdk.blackcore.rcugroup;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupUpdateRoomEnity;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 * @param <T>
 *
 */
public class RoomUpdateRcuGroupClient<T> extends AbsHttpClient<T> {

	private RcuGroupUpdateRoomEnity request;

	public RoomUpdateRcuGroupClient(String host, RcuGroupUpdateRoomEnity request) {
		super(host, "/rcu/updateroomrcugroup");
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
