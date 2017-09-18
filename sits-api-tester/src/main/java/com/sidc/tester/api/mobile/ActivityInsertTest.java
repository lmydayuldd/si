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
import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeInsertBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityLangBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatFrequentBean;
import com.sidc.blackcore.api.mobile.activity.request.ActivityInsertRequest;

public class ActivityInsertTest {

	@Test
	public void test() throws Exception {
		List<ActivityLangBean> list = new ArrayList<ActivityLangBean>();
		List<ActivityFeeInsertBean> feelist = new ArrayList<ActivityFeeInsertBean>();
		List<ActivityRepeatBean> repeatlist = new ArrayList<ActivityRepeatBean>();
		List<ActivityPhotoUploadBean> photoList = new ArrayList<ActivityPhotoUploadBean>();

		String[] repeat = { "4" };
		final ActivityRepeatFrequentBean repeatFrequentEntity = new ActivityRepeatFrequentBean(repeat);

		list.add(new ActivityLangBean("zh_TW", "標題", "內容", "位置"));
		list.add(new ActivityLangBean("en_US", "tiitle", "content", "location"));

		feelist.add(new ActivityFeeInsertBean(1, "100"));

		repeatlist.add(new ActivityRepeatBean("2017/08/18 07:00", "2017/08/28 17:00", "descri"));
		// repeatlist.add(new ActivityRepeatBean("2017/08/28 08:00", "2017/06/28
		// 17:00", null));

		photoList.add(new ActivityPhotoUploadBean(getByteArray("C:\\Users\\123\\Desktop\\S__8831044.jpg"),
				UUID.randomUUID().toString().replace("-", ""), "jpg"));

		ActivityInsertRequest request = new ActivityInsertRequest("34a2a361-0dc0-4198-9d69-b06900234aa4", 1, "3", 0, 2,
				1, "2017/09/14 07:00", "2017/09/30 19:00", 1, "memo", repeatFrequentEntity, list, feelist, repeatlist,
				null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/sits/activity/insert");

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

	// @Test
	// public void aatet() throws IOException {
	// try {
	// byte[] imageInByte;
	// BufferedImage originalImage = ImageIO.read(new
	// File("C:\\Users\\123\\Desktop\\S__8831044.jpg"));
	//
	// // convert BufferedImage to byte array
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// ImageIO.write(originalImage, "jpg", baos);
	// baos.flush();
	//
	// imageInByte = baos.toByteArray();
	// baos.close();
	// System.out.println("encossded:" +
	// java.util.Arrays.toString(imageInByte));
	//
	// String decoded = new String(imageInByte, "ISO-8859-1");
	// System.out.println("decoded:" + decoded);
	//
	// byte[] encoded = decoded.getBytes("ISO-8859-1");
	// System.out.println("encoded:" + java.util.Arrays.toString(encoded));
	//
	// // convert byte array back to BufferedImage
	// InputStream in = new ByteArrayInputStream(imageInByte);
	// BufferedImage bImageFromConvert = ImageIO.read(in);
	//
	// // ImageIO.write(bImageFromConvert, "png", new
	// // File("/var/opt/Output.png"));
	//
	// // ImageIO.write(bImageFromConvert, "png", new
	// // File("D:\\Output.png"));
	//
	// } catch (IOException e) {
	// System.out.println(e.getMessage());
	// }
	// }

}
