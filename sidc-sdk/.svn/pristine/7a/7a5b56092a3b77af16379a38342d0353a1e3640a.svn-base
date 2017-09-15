package com.sidc.sdk.blackcore.rcu.command;

import com.derex.cm.stb.api.request.APIRequest;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.sdk.abs.AbsHttpClient;

/**
 * 
 * @author Allen Huang
 *
 * @param <T>
 */
public class HvacCommandClient<T> extends AbsHttpClient<T> {

	private RCUCommander request;
	public HvacCommandClient(String host, RCUCommander request) {
		super(host, "/rcu/hvaccommand");
		this.request = request;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String request() throws Exception {
		// TODO Auto-generated method stub
		APIRequest request = new APIRequest(this.request);

		return super.getGson().toJson(request);
	}

	@Override
	protected Object response(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
