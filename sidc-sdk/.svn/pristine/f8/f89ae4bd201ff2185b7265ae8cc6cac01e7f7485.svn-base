/**
 * 
 */
package com.sidc.sdk.sits;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.ra.request.RoomModuleRequest;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 * @param <T>
 *
 */
public class ZhongshanInitialClient<T> extends AbsHttpClient<T> {

	private RoomModuleRequest request;

	public ZhongshanInitialClient(String host, RoomModuleRequest request) {
		super(host, "/zhongshanrcumoduleinitial");
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
