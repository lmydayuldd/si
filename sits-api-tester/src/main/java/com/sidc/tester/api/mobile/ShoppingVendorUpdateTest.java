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
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingVendorUpdateRequest;

public class ShoppingVendorUpdateTest {

	@Test
	public void test() throws Exception {

		List<ShopLangBean> list = new ArrayList<ShopLangBean>();
		list.add(new ShopLangBean("鴻海精密", "zh_TW", "2"));
		list.add(new ShopLangBean("redd", "en_US", "2"));

		ShoppingVendorUpdateRequest request = new ShoppingVendorUpdateRequest("708b5c67-9b9d-438e-9439-a949c4db521", 4,
				2, "0505", "jfaaaao@jtoao.com", "w", list);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.251:7056/blackcore/sits/shopping/vendorupdate");

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
