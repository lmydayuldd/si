package com.sidc.tester.api.scenario;

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
import com.sidc.blackcore.api.sits.token.bean.InfoBean;
import com.sidc.blackcore.api.sits.token.request.MobilePrivateTokenInsertRequest;

public class MobilePrivateTokenInsertTest {

	@Test
	public void test() {
		InfoBean info = new InfoBean("99669w5", 1, "6.0.1","s");
		MobilePrivateTokenInsertRequest request = new MobilePrivateTokenInsertRequest(
				"6b96eca8-085c-49e0-bb1a-64ab06e69b5b", "608", "10.60.6.8", 0, info);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.251:7056/blackcore/mobile/token/privateinsert");

		APIRequest enity = new APIRequest(request);
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
