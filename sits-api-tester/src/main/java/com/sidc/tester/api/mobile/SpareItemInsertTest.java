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
import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;
import com.sidc.blackcore.api.sits.spare.request.SpareItemInsertRequest;

public class SpareItemInsertTest {

	@Test
	public void test() throws Exception {

		List<ActivityPhotoUploadBean> photoList = new ArrayList<ActivityPhotoUploadBean>();
		photoList.add(new ActivityPhotoUploadBean(getByteArray("C:\\Users\\123\\Desktop\\b.jpg"),
				UUID.randomUUID().toString().replace("-", ""), "jpg"));

		List<SpareLangBean> list = new ArrayList<SpareLangBean>();
		list.add(new SpareLangBean("備品項目測試3.", "zh_TW", "備品3."));
		list.add(new SpareLangBean("spare item test3.", "en_US", "item3."));

		// private String token;
		// private int categoryid;
		// private String status;
		// private int sequence;
		// private float price;
		// private int qty;

		SpareItemInsertRequest request = new SpareItemInsertRequest("86f6ffa3-2d69-4389-b044-664b2a3ba130", 6, "0", 3,
				(float) 33, 33, list, photoList);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/sits/spare/iteminsert");

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
