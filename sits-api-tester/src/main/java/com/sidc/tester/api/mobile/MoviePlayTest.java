package com.sidc.tester.api.mobile;

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
import com.sidc.blackcore.api.sits.movie.request.MoviePlayRequest;

public class MoviePlayTest {

	@Test
	public void test() {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.3.251:7056/blackcore/sits/movieplay");
//
		APIRequest enity = new APIRequest(
				new MoviePlayRequest("79cdacb0-aef2-44ca-bb60-f8d4a2fc7b2e", "0ec7e368-c824-4395-a83a-9802c7832778",
						"zh_Tw", "608", "10.60.6.08", "4028b8815e4ba05b015e4be87a8210f1", true));
		
////		.3 805
//		APIRequest enity = new APIRequest(
//				new MoviePlayRequest("3cf4436e-c6d2-4368-ac64-cd8534cd0924", "d2bf5d3c-1c1f-47fd-ab31-d2cb2db4b5dd",
//						"zh_Tw", "805", "10.60.8.05", "4028b8815e4ba05b015e4be87a8210f1", true));

		// APIRequest enity = new APIRequest(
		// new MoviePlayRequest("95da8d5c-0880-44cb-b331-1b637c30454a",
		// "31d24df0-4a05-4756-95f7-c437d3b2f9e2",
		// "zh_Tw", "610", "10.60.6.10", "bc7aa6895dea27a5015dedc4d6df36ab"));

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
