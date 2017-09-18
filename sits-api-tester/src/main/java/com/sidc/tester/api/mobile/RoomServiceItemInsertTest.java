package com.sidc.tester.api.mobile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceItemInsertRequest;

public class RoomServiceItemInsertTest {

	@Test
	public void test() throws Exception {
		List<ActivityPhotoUploadBean> photoList = new ArrayList<ActivityPhotoUploadBean>();
		photoList.add(new ActivityPhotoUploadBean(getByteArray("C:\\Users\\123\\Desktop\\j.jpg"),
				UUID.randomUUID().toString().replace("-", ""), "jpg"));

		List<RoomServiceItemIdBean> itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(1));
		itemlist.add(new RoomServiceItemIdBean(2));
		itemlist.add(new RoomServiceItemIdBean(3));

		List<RoomServiceSetBean> setlist = new ArrayList<RoomServiceSetBean>();
		RoomServiceSetBean setBena = new RoomServiceSetBean(5, 1, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(4));
		itemlist.add(new RoomServiceItemIdBean(5));
		itemlist.add(new RoomServiceItemIdBean(6));
		itemlist.add(new RoomServiceItemIdBean(7));
		itemlist.add(new RoomServiceItemIdBean(8));
		itemlist.add(new RoomServiceItemIdBean(9));
		itemlist.add(new RoomServiceItemIdBean(10));
		itemlist.add(new RoomServiceItemIdBean(11));
		itemlist.add(new RoomServiceItemIdBean(12));
		setBena = new RoomServiceSetBean(6, 3, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(13));
		itemlist.add(new RoomServiceItemIdBean(14));
		itemlist.add(new RoomServiceItemIdBean(15));
		itemlist.add(new RoomServiceItemIdBean(16));
		setBena = new RoomServiceSetBean(7, 1, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(17));
		itemlist.add(new RoomServiceItemIdBean(18));
		itemlist.add(new RoomServiceItemIdBean(19));
		itemlist.add(new RoomServiceItemIdBean(20));
		itemlist.add(new RoomServiceItemIdBean(21));
		setBena = new RoomServiceSetBean(8, 1, itemlist);
		setlist.add(setBena);

		itemlist = new ArrayList<RoomServiceItemIdBean>();
		itemlist.add(new RoomServiceItemIdBean(22));
		setBena = new RoomServiceSetBean(9, 1, itemlist);
		setlist.add(setBena);

		List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
		list.add(new RoomServiceLangBean("zh_TW", "奶茶", null));

		RoomServiceItemInsertRequest request = new RoomServiceItemInsertRequest("5d99048a-9897-48ce-bebc-d0d50039ed76", 24, 1, 0, (float) 50, "single",
				list, setlist, photoList);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.252:7056/blackcore/sits/inroomdining/iteminsert");

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
		byte[] imageInByte = new byte[1];
		BufferedImage originalImage = ImageIO.read(new File(fileName));

		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();

		imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}

	public long humanReadableByteCount(long bytes) {
		return (long) (Math.log(bytes));
	}

	public String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}
