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
import com.sidc.blackcore.api.sits.spare.bean.SpareOrderItemBean;
import com.sidc.blackcore.api.sits.spare.request.SpareOrderCreateRequest;

public class SpareOrderCreateTest {

	@Test
	public void test() throws Exception {

		List<SpareOrderItemBean> itemList = new ArrayList<SpareOrderItemBean>();

		itemList.add(new SpareOrderItemBean(4, 1));

		// private String publickey;
		// private String privatekey;
		// private String roomno;
		// private String guestno;
		// private String guestfirstname;
		// private String guestlastname;
		// private String memo;

		SpareOrderCreateRequest request = new SpareOrderCreateRequest("2f0034a4-a49b-434c-88c0-0d69ff00a142",
				"4420ded2-ce3e-4707-8bfc-a96ca41ccfc0", "608", "1", "fist", "last", "memo", itemList);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/sits/spare/ordercreate");

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
