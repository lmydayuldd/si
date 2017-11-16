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
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;
import com.sidc.blackcore.api.mobile.laundry.request.LaundryClassInsertRequest;

public class LaundryClassInsertTest {

	@Test
	public void test() throws Exception {
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();

		list.add(new LaundryLangBean("zh_TW", "隔天喜", "隔天送回."));
		list.add(
				new LaundryLangBean("en_US", "tomorrow", "tomorrow"));

		LaundryClassInsertRequest request = new LaundryClassInsertRequest("token", 1, (float) 0.0, list);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/sits/laundryservice/classinsert");

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
