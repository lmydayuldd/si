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
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryOrderItemBean;
import com.sidc.blackcore.api.mobile.laundry.request.LaundryOrderCreateRequest;

public class LaundryOrderCreateTest {

	@Test
	public void test() throws Exception {
		List<LaundryOrderItemBean> itemlist = new ArrayList<LaundryOrderItemBean>();

		// this.itemid = itemid;
		// this.washtypeid = washtypeid;
		// this.returntypeid = returntypeid;
		// this.piece = piece;
		//
		itemlist.add(new LaundryOrderItemBean(3, 2, 1, 100));
//		itemlist.add(new LaundryOrderItemBean(2, 2, 1, 200));

		// publickey;
		// privatekey;
		// roomno;
		// gusetno;
		// gusetfirstname;
		// gusetlastname;
		// receivetime;
		// classid;
		// memo;
		// itemlist;

		LaundryOrderCreateRequest request = new LaundryOrderCreateRequest("00996f30-e2bb-4430-bbf0-104d5a4cc4c6",
				"0a4b0120-4a66-48b9-ab9d-46b519efa6da", "610", "GuestNo", "Apple2", "Siri2", null, 3, "memo2",
				itemlist);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/sits/laundryservice/ordercreate");

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
