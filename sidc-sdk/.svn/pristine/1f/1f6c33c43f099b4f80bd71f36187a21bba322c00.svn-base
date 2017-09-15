package com.sidc.sdk.agent;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.blackcore.api.agent.request.AgentPostRequest;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * 
 * @author Allen Huang
 *
 */
public class PostClient extends AbsHttpClient {

	private AgentPostRequest enity;
	public PostClient(String host, AgentPostRequest enity) {
		super(host, "/post");
		this.enity = enity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String request() throws Exception {
		// TODO Auto-generated method stub
		APIRequest request = new APIRequest(enity);
		
		return super.getGson().toJson(request);
	}

	@Override
	protected Object response(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
