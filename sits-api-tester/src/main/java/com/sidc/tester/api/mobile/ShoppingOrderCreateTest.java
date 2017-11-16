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
import com.sidc.blackcore.api.sits.shop.bean.ShopOrderItemBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingOrderCreateRequest;

public class ShoppingOrderCreateTest {

	@Test
	public void test() throws Exception {
		List<ShopOrderItemBean> itemlist = new ArrayList<ShopOrderItemBean>();
		itemlist.add(new ShopOrderItemBean(10, 1));

		// this.publickey = publickey;
		// this.privatekey = privatekey;
		// this.roomno = roomno;
		// this.guestno = guestno;
		// this.guestfirstname = guestfirstname;
		// this.guestlastname = guestlastname;
		// this.memo = memo;
		// this.itemlist = itemlist;

		ShoppingOrderCreateRequest request = new ShoppingOrderCreateRequest("1c053e0d-04a6-4683-926f-1fcd75c4bd81",
				"67578df4-fbfa-4b93-9085-76aec3d3ee98", "610", "", "", "", "", itemlist);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.38:7056/blackcore/sits/shopping/ordercreate");

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
