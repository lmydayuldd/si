/**
 * 
 */
package com.sidc.rcu.hmi.framework.abs;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.sidc.rcu.hmi.api.request.APIRequest;
import com.sidc.utils.exception.SiDCException;

public abstract class AbstractAPIProcess {

	public AbstractAPIProcess() {
		super();
	}

	public final Object execute() throws SiDCException, Exception {

		init();

		check();

		Object obj = process();

		return obj;

	}

	protected abstract void init() throws SiDCException, Exception;

	protected abstract Object process() throws SiDCException, Exception;

	protected abstract void check() throws SiDCException, Exception;

	public String sendPost(String url) {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);

		// Request parameters and other properties.
		APIRequest enity = new APIRequest(null);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		String result = null;
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			// httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
