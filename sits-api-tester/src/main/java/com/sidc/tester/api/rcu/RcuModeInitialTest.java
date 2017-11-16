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
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialDevicesBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialGroupBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeLangBean;
import com.sidc.blackcore.api.ra.rcumode.request.ModeInitialRequest;

public class RcuModeInitialTest {

	@Test
	public void test() throws Exception {

		List<ModeInitialDevicesBean> modedevices = new ArrayList<ModeInitialDevicesBean>();
		modedevices.add(new ModeInitialDevicesBean("DESK", 0, false, 0, 0, 0, 0));
		modedevices.add(new ModeInitialDevicesBean("WALL", 0, false, 0, 0, 0, 0));
		// modedevices.add(new ModeInitialDevicesBean("HVAC-ALL", 0, true, 1,
		// 20, 1, 0));

		List<ModeInitialGroupBean> groups = new ArrayList<ModeInitialGroupBean>();
		groups.add(new ModeInitialGroupBean(1, modedevices));
		groups.add(new ModeInitialGroupBean(2, modedevices));

		List<ModeLangBean> langs = new ArrayList<ModeLangBean>();
		langs.add(new ModeLangBean("lang", "name", "des"));

		List<ModeInitialBean> modes = new ArrayList<ModeInitialBean>();
		modes.add(new ModeInitialBean(1, "checkin", 0, groups, langs));

		langs = new ArrayList<ModeLangBean>();
		langs.add(new ModeLangBean("lang", "name2", "des"));
		modes.add(new ModeInitialBean(2, "checkout", 0, groups, langs));

		ModeInitialRequest request = new ModeInitialRequest(modes);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.38:7056/blackcore/rcumodeinitial");

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
