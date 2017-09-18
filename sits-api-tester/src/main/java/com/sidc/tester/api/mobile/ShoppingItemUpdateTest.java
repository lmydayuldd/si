package com.sidc.tester.api.mobile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

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
import com.sidc.blackcore.api.sits.shop.bean.ShopItemLangBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopPhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingItemUpdateRequest;

public class ShoppingItemUpdateTest {

	@Test
	public void test() throws Exception {

		List<ShopPhotoUploadBean> photoList = new ArrayList<ShopPhotoUploadBean>();
		photoList.add(new ShopPhotoUploadBean(getByteArray("C:\\Users\\123\\Desktop\\b.jpg"),
				UUID.randomUUID().toString().replace("-", ""), "jpg"));

		List<ShopItemLangBean> list = new ArrayList<ShopItemLangBean>();
		list.add(new ShopItemLangBean("熱線你和我", "zh_TW", "", null));

		// token;
		// item id
		// categoryid;
		// vendorid;
		// status;
		// sequence;
		// price;
		// sellingprice;
		// qty;
		// weight;
		// list;
		// photolist;
		ShoppingItemUpdateRequest request = new ShoppingItemUpdateRequest("940ef382-036a-4fef-bbd6-27413d628c74", 6, 4,
				3, "1", 100, (float) 50, (float) 50, 100, (float) 100, list, null, false);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.251:7056/blackcore/sits/shopping/itemupdate");

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

	private byte[] getByteArray(final String fileName) throws IOException {
		byte[] imageInByte;
		BufferedImage originalImage = ImageIO.read(new File(fileName));

		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();

		imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}
}
