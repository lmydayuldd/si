package com.sidc.blackcore.sits.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.derex.cm.sits.api.request.PhotoUploadRequest;
import com.derex.cm.stb.api.request.APIRequest;
import com.google.gson.Gson;

public class PhotoUploadTest {

	@Test
	public void test() throws Exception {

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:8080/sits/sits/api/photoupload.json");

		APIRequest enity = new APIRequest(null);
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

	public byte[] getArray() throws IOException {
		try {
			byte[] imageInByte;
			BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\123\\Desktop\\test.png"));

			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();

			imageInByte = baos.toByteArray();
			baos.close();

			return imageInByte;

		} catch (IOException e) {
			return null;
		}
	}

}
