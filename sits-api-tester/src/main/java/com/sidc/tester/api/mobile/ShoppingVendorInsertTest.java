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
import com.sidc.blackcore.api.sits.shop.request.ShoppingVendorInsertRequest;

public class ShoppingVendorInsertTest {

	@Test
	public void test() throws Exception {

		List<ShopLangBean> list = new ArrayList<ShopLangBean>();
		list.add(new ShopLangBean("鴻海精密", "zh_TW", ""));
		list.add(new ShopLangBean("red", "en_US", ""));

		ShoppingVendorInsertRequest request = new ShoppingVendorInsertRequest("86f6ffa3-2d69-4389-b044-664b2a3ba130", 1,
				"02dd2587441", "hihi@rd.com.tw", "台北2red", list);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.251:7056/blackcore/sits/shopping/vendorinsert");

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
