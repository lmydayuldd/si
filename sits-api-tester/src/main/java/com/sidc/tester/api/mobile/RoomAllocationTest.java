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
import com.sidc.blackcore.api.sits.roomallocation.bean.RoomListBean;
import com.sidc.blackcore.api.sits.roomallocation.request.RoomAllocationRequest;

public class RoomAllocationTest {

	@Test
	public void test() {

		List<RoomListBean> roomlist = new ArrayList<RoomListBean>();
		roomlist.add(new RoomListBean("510"));
		roomlist.add(new RoomListBean("511"));

		RoomAllocationRequest request = new RoomAllocationRequest("a+OV2J8TT+vTrTZoSXlb8jcFVmk0/VPQvnxOsRXAWgHS3KwnPaxRWb6P50QcDOxa", "user@sidc.com", roomlist);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/sits/roomallocation");

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
