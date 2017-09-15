package com.sidc.sits.logical.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.PhotoManager;
import com.sidc.sits.logical.parameter.PageList;

public class ImageUtils {

	public static String httpFileToBase64(final String fileUrl) throws IOException {
		URL url = new URL(fileUrl);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			is = url.openStream();

			byte[] byteChunk = new byte[4096];
			int n;

			while ((n = is.read(byteChunk)) > 0)
				os.write(byteChunk, 0, n);

		} finally {
			if (os != null) {
				os.close();
			}
			if (is != null) {
				is.close();
			}

		}
		Base64 base64 = new Base64();
		final String base64Img = new String(base64.encode(os.toByteArray()));

		return base64Img;
	}

	public static void sendPhotoToSits(final String referId, final List<String> folderList,
			final List<PhotoUploadBean> photoList, final String type) throws IOException, SQLException {
		// 通知 sits 上傳圖片
		try {
			final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());

			HttpClientUtils.sendPostWithUploadPhoto(sitsUrl + PageList.UPLOAD_PHOTO, folderList, type, photoList);

		} catch (Exception e) {
			// 上傳失敗 備份
			for (final PhotoUploadBean photoEntity : photoList) {
				PhotoManager.getInstance().updateWithBackup(referId, "2", photoEntity.getPhoto());
			}
		}
	}
}
