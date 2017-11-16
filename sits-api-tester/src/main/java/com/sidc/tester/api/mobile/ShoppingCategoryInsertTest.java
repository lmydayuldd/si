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
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingCategoryInsertRequest;

public class ShoppingCategoryInsertTest {

	@Test
	public void test() throws Exception {

		List<ActivityPhotoUploadBean> photoList = new ArrayList<ActivityPhotoUploadBean>();
		photoList.add(new ActivityPhotoUploadBean(getByteArray("C:\\Users\\123\\Desktop\\b.jpg"),
				UUID.randomUUID().toString().replace("-", ""), "jpg"));

		List<ShopLangBean> list = new ArrayList<ShopLangBean>();
		list.add(new ShopLangBean("旅遊良伴", "zh_TW", ""));
		list.add(new ShopLangBean("tourism", "en_US", "tourism"));

		// token;
		// status;
		// referid;
		// sequence;
		// list;
		// photolist;

		ShoppingCategoryInsertRequest request = new ShoppingCategoryInsertRequest(
				"86f6ffa3-2d69-4389-b044-664b2a3ba130", 0, 0, 50, list, new ArrayList<ActivityPhotoUploadBean>());

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.251:7056/blackcore/sits/shopping/categoryinsert");

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
