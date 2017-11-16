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
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceCategoryUpdateRequest;

public class RoomServiceCategoryUpdateTest {

	@Test
	public void test() throws Exception {
		List<ActivityPhotoUploadBean> photoList = new ArrayList<ActivityPhotoUploadBean>();
		photoList.add(new ActivityPhotoUploadBean(getByteArray("C:\\Users\\123\\Desktop\\mur-off.png"),
				UUID.randomUUID().toString().replace("-", ""), "png"));

		List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
		list.add(new RoomServiceLangBean("en_US", "break first", null));
		list.add(new RoomServiceLangBean("zh_TW", "早餐", "超營養早餐"));

		RoomServiceCategoryUpdateRequest request = new RoomServiceCategoryUpdateRequest(
				"44d7a51d-e185-45a3-8a43-e918b1cb70e4", 18, 0, 1, null, null, 0, list, photoList, true);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://60.250.38.8:7056/blackcore/sits/inroomdining/categoryupdate");

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
