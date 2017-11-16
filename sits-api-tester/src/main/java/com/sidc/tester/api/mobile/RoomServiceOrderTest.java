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
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderSetBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderSetItemBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemListBean;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCreateOrderRequest;

public class RoomServiceOrderTest {

	@Test
	public void test() throws Exception {
		List<RoomServiceOrderLineBean> list = new ArrayList<RoomServiceOrderLineBean>();
		list.add(new RoomServiceOrderLineBean(43, 2));
		list.add(new RoomServiceOrderLineBean(44, 1));

		List<RoomServiceOrderSetBean> setlist = new ArrayList<RoomServiceOrderSetBean>();
		List<RoomServiceOrderSetItemBean> itemlist = new ArrayList<RoomServiceOrderSetItemBean>();
		itemlist.add(new RoomServiceOrderSetItemBean(1));

		List<RoomServiceSetItemListBean> iteminfo = new ArrayList<RoomServiceSetItemListBean>();
		iteminfo.add(new RoomServiceSetItemListBean(itemlist));
		// iteminfo.add(new RoomServiceSetItemListBean(itemlist));

		RoomServiceOrderSetBean setBean = new RoomServiceOrderSetBean(23, 1, iteminfo, itemlist);
		setlist.add(setBean);

		RoomServiceCreateOrderRequest request = new RoomServiceCreateOrderRequest(
				"0b371f78-c354-4bab-b897-a112bebf914d", "c55aa4e8-1bef-4c19-9b53-e85ed6c267b2", "9999", null, "sidc",
				"sidc", "", "memo", list, null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://61.221.43.161:7056/blackcore/sits/inroomdining/order");

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
