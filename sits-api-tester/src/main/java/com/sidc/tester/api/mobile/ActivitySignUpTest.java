package com.sidc.tester.api.mobile;

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
import com.sidc.blackcore.api.mobile.activity.bean.ActivitySignUpBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivitySignUpRequest;

public class ActivitySignUpTest {

	@Test
	public void test() throws Exception {
		List<ActivitySignUpBean> list = new ArrayList<ActivitySignUpBean>();
		list.add(new ActivitySignUpBean(1, "SIDC_GUEST_first1", "last2", 1, "elderly", "Aa23aw8a887e2", "AaAd62a6w8",
				"phone", "email", ""));

//		list.add(new ActivitySignUpBean(1, "fistname", "lastname", 1, "elderly", "A12388778w72", "AA9w9W6658", "phone",
//				"email", ""));

		ActivitySignUpRequest request = new ActivitySignUpRequest("63261e16-8914-41d4-9e1e-ddecc3a8fd6b",
				"7e9a6a48-7016-4d9e-965d-c84a47c951b8", 3, 4, "808", "momo", list, "2017/08/21");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.251:7056/blackcore/sits/activity/ordercreate");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
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
