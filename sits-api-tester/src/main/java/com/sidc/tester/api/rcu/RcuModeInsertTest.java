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
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceBulbSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuDeviceHvacSettingBean;
import com.sidc.blackcore.api.ra.rcumodesetting.request.RcuGroupModeInsertRequest;

public class RcuModeInsertTest {

	@Test
	public void test() throws Exception {
		List<RcuDeviceBean> devices = new ArrayList<RcuDeviceBean>();

		RcuDeviceHvacSettingBean hvac = new RcuDeviceHvacSettingBean(false, 0, 0, 0, 0, 0);
		devices.add(new RcuDeviceBean("HVAC-ALL", hvac));

		RcuDeviceBulbSettingBean b = new RcuDeviceBulbSettingBean(0);
		devices.add(new RcuDeviceBean("BATH", b));

		RcuGroupModeInsertRequest request = new RcuGroupModeInsertRequest(1, 1, devices);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/rcu/mode/insert");

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
