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
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemIdBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetBean;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceItemUpdateRequest;

public class RoomServiceItemUpdateTest {

	@Test
	public void test() throws Exception {
		List<ActivityPhotoUploadBean> photoList = new ArrayList<ActivityPhotoUploadBean>();
		photoList.add(new ActivityPhotoUploadBean(getByteArray("C:\\Users\\123\\Desktop\\mur-off.png"),
				UUID.randomUUID().toString().replace("-", ""), "png"));

		List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
		list.add(new RoomServiceLangBean("zh_TW", "可頌", "好好喝"));

		List<RoomServiceItemIdBean> itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(10));
		itemlist.add(new RoomServiceItemIdBean(11));
		itemlist.add(new RoomServiceItemIdBean(12));

		List<RoomServiceSetBean> setlist = new ArrayList<RoomServiceSetBean>();
		RoomServiceSetBean setBena = new RoomServiceSetBean(9, 1, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(13));
		itemlist.add(new RoomServiceItemIdBean(14));
		itemlist.add(new RoomServiceItemIdBean(15));
		itemlist.add(new RoomServiceItemIdBean(16));
		itemlist.add(new RoomServiceItemIdBean(17));
		itemlist.add(new RoomServiceItemIdBean(18));
		setBena = new RoomServiceSetBean(10, 2, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(19));
		itemlist.add(new RoomServiceItemIdBean(20));
		itemlist.add(new RoomServiceItemIdBean(21));
		itemlist.add(new RoomServiceItemIdBean(22));
		itemlist.add(new RoomServiceItemIdBean(17));
		itemlist.add(new RoomServiceItemIdBean(18));
		setBena = new RoomServiceSetBean(11, 1, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(23));
		itemlist.add(new RoomServiceItemIdBean(24));
		itemlist.add(new RoomServiceItemIdBean(25));
		setBena = new RoomServiceSetBean(12, 2, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(26));
		setBena = new RoomServiceSetBean(13, 0, itemlist);
		setlist.add(setBena);

		RoomServiceItemUpdateRequest request = new RoomServiceItemUpdateRequest("44d7a51d-e185-45a3-8a43-e918b1cb70e4",
				13, 10, 1, 1, (float) 60, "single", list, setlist, photoList, true);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:7056/blackcore/sits/inroomdining/itemupdate");

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
		ImageIO.write(originalImage, "png", baos);
		baos.flush();

		imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}
}
