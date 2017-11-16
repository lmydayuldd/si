/**
 * 
 */
package com.sidc.sdk.sits;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.ra.rcumode.request.ModeInitialRequest;
import com.sidc.sdk.abs.AbsHttpClient;

public class ModeInitialClient<T> extends AbsHttpClient<T> {

	private ModeInitialRequest request;

	public ModeInitialClient(String host, ModeInitialRequest request) {
		super(host, "/rcumodeinitial");
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
