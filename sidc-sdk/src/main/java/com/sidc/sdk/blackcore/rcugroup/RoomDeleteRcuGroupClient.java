/**
 * 
 */
package com.sidc.sdk.blackcore.rcugroup;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.ra.rcugroup.request.RcuGroupDeleteRoomEnity;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 * @param <T>
 *
 */
public class RoomDeleteRcuGroupClient<T> extends AbsHttpClient<T> {

	private RcuGroupDeleteRoomEnity request;

	public RoomDeleteRcuGroupClient(String host, RcuGroupDeleteRoomEnity request) {
		super(host, "/rcu/deleteroomrcugroup");
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
