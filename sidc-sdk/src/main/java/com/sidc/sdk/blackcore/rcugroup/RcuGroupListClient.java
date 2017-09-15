/**
 * 
 */
package com.sidc.sdk.blackcore.rcugroup;

import java.util.List;

import com.derex.cm.stb.api.request.APIRequest;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.response.RcuGroupEnity;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * @author Nandin
 *
 */
public class RcuGroupListClient extends AbsHttpClient<List<RcuGroupEnity>> {

	public RcuGroupListClient(String host) {
		super(host, "/rcu/listrcugroup");
	}

	@Override
	public String request() throws Exception {

		APIRequest request = new APIRequest(null);

		return super.getGson().toJson(request);
	}

	@Override
	public List<RcuGroupEnity> response(String json) throws Exception {

		if (json == null || json.length() == 0) {
			return null;
		}

		List<RcuGroupEnity> enity = super.getGson().fromJson(json, new TypeToken<List<RcuGroupEnity>>() {
		}.getType());

		return enity;
	}

}
