package com.sidc.blackcore.sits.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.google.gson.Gson;

public class fcm {
	public final String AUTH_KEY_FCM = "AAAAGlxgTc0:APA91bFYoUbaQPzjDm3W-sFvI8d0CCRt2ONECLaqOpLFbxIPcciWQI5WF3ay3KbIX1GEcLFxNQ20jN2NX3XoCEXEeDZx_UPn0_CPpr6-t2y8Gfblc84d2U34G4E1fMwDth2vtZEeOw07";
	public final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	@Test
	public void test3() throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("a", "1");
		hm.put("b", "1");

		for (Map.Entry<String, String> entity : hm.entrySet()) {
			System.out.println("key:" + entity.getKey());
			System.out.println("value:" + entity.getValue());
		}
	}

	@Test
	public void test2() throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).build();
		HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpPost httppost = new HttpPost(API_URL_FCM);
		String userDeviceIdKey = "fjG8ChR7va4:APA91bHB2K0vx60LL5e8tZ05kVlCeEfKnrdUxvYjYBzSKLm4FEkUUAuGVNj26CTiqbjQhjfAOqvUL_qu7Id9Z4RFgTF19VFdDEvWvriqtYk0E--GFRt2NBXv23HFxckylMqf57fQtnuK";

		String result = null;
		List<String> list = new ArrayList<String>();
		list.add(userDeviceIdKey);

		// FcmMessageBean aaa = new FcmMessageBean("123", "dsa", "dsa");
		// final FcmBean enity = new FcmBean(list, aaa);

		final Gson gson = new Gson();
		final String json = gson.toJson(null);

		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		try {
			httppost.setHeader("Content-Type", "application/json");
			httppost.setHeader("Authorization",
					"key=AAAAGlxgTc0:APA91bFYoUbaQPzjDm3W-sFvI8d0CCRt2ONECLaqOpLFbxIPcciWQI5WF3ay3KbIX1GEcLFxNQ20jN2NX3XoCEXEeDZx_UPn0_CPpr6-t2y8Gfblc84d2U34G4E1fMwDth2vtZEeOw07");

			httppost.setEntity(strEntity);
			HttpResponse response = null;

			response = httpclient.execute(httppost);

			HttpEntity resultEnity = response.getEntity();

			InputStream instreams = resultEnity.getContent();
			result = parseStreamToString(instreams);

			System.out.println(result);
		} finally {
			httppost.releaseConnection();
		}
	}

	public String parseStreamToString(InputStream in) throws Exception {

		ByteArrayOutputStream outStream = null;

		String result = null;
		try {
			outStream = new ByteArrayOutputStream();
			byte[] data = new byte[4096];
			int count = -1;
			while ((count = in.read(data, 0, 4096)) != -1)
				outStream.write(data, 0, count);

			data = null;

			result = new String(outStream.toByteArray(), "UTF-8");

		} finally {
			outStream.flush();
			outStream.close();
		}

		return result;
	}

}
