package com.sidc.tester.api.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.blackcore.api.agent.request.GuestRequest;
import com.sidc.blackcore.api.agent.request.MessageRequest;

/**
 * 
 * @author Joe
 *
 */
public class STBMessageTest {

	@Test
	public void test() {
		
//		this.roomno = roomno;
//		this.caller = caller;
//		this.text = text;
//		this.receiver = receiver;
//		this.createuser = createuser;
//		this.isfrompms = isfrompms;
		MessageRequest request = new MessageRequest("415", "Joe", "TV__TEST", "", "crate", false);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://123.51.2168.223:7056/blackcore/messagebuild");

		// Request parameters and other properties.
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
		// }

	}
}
