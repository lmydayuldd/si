package com.sidc.tester.api.rcu;

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
import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceInsertBean;
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuGroupDeviceInsertRequest;

public class RcuGroupDeviceInsertTest {

	@Test
	public void test() throws Exception {
		List<Integer> devices = new ArrayList<Integer>();
		devices.add(1);
		devices.add(2);
		devices.add(3);
		devices.add(4);
		devices.add(5);

		List<RcuGroupDeviceInsertBean> groups = new ArrayList<RcuGroupDeviceInsertBean>();
		groups.add(new RcuGroupDeviceInsertBean(1, devices));

		RcuGroupDeviceInsertRequest request = new RcuGroupDeviceInsertRequest(groups);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/rcu/group/device/insert");

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
