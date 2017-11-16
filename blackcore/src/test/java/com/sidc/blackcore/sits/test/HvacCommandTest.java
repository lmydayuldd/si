package com.sidc.blackcore.sits.test;

import java.io.IOException;

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
import com.sidc.blackcore.api.sits.token.bean.InfoBean;
import com.sidc.blackcore.api.sits.token.request.MobilePrivateTokenInsertRequest;
import com.sidc.utils.encrypt.AesEncrypt;

public class HvacCommandTest {

	@Test
	public void test() throws Exception {
		String key = "YLKrnoucAmtC/Ix37f5S9Q==";
		AesEncrypt encrypt = new AesEncrypt("sidc");
		encrypt.decrypt(key);
		
//		yJ2mkJh0+nLjtER3odl4YtlqXJkYboESOidg/rREXM7RK4OlE7ssNxTb6rpo+gDR

		System.out.println("解密:" + encrypt.getDecryptedString());

		InfoBean info = new InfoBean("99966452", 1, "6.0.1", "ss3");
		MobilePrivateTokenInsertRequest request = new MobilePrivateTokenInsertRequest(key, "610", "10.60.6.10", 0,
				info);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.38:7056/blackcore/mobile/token");

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
