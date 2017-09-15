package com.sidc.servic.api.agent.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.derex.cm.stb.api.request.APIRequest;
import com.google.gson.Gson;
import com.sidc.blackcore.api.agent.request.AgentPostRequest;

/**
 * 
 * @author Allen Huang
 *
 */
public class PostTest {

	@Test
	public void test() {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://127.0.0.1:8080/sidc-service/post");
		
		// Request parameters and other properties.
		APIRequest enity = new APIRequest(new AgentPostRequest("POSTED", "502", "5fa620e9-983d-4d9c-9a47-605232d9245b"));
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
	        HttpResponse response = httpclient.execute(httppost);
	        String result = EntityUtils.toString(response.getEntity());
	        System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
