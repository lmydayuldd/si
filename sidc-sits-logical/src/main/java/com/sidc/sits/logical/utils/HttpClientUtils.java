package com.sidc.sits.logical.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import com.derex.cm.sits.api.bean.ApidocumentBean;
import com.derex.cm.sits.api.bean.PhotoUploadBean;
import com.derex.cm.sits.api.request.PhotoDeleteRequest;
import com.derex.cm.sits.api.request.PhotoUploadRequest;
import com.derex.cm.stb.api.request.APIRequest;
import com.derex.cm.stb.api.request.StbCheckoutRequest;
import com.derex.cm.stb.api.request.StbMessageRequest;
import com.derex.cm.stb.api.request.StbMoviePlayRequest;
import com.derex.cm.stb.api.request.StbOpenTvByChannelNoRequest;
import com.derex.cm.stb.api.request.StbRebootRequest;
import com.derex.cm.stb.api.request.StbRedirectPageRequest;
import com.google.gson.Gson;
import com.sidc.blackcore.api.mobile.message.bean.FcmBean;
import com.sidc.blackcore.api.sits.hotelinfo.request.HotelInfoRequest;
import com.sidc.blackcore.api.sits.hotelinfo.response.HotelInfoResponse;
import com.sidc.blackcore.api.sits.printer.request.PrinterRequest;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;
import com.sidc.utils.status.SystemStatus;

public class HttpClientUtils {

	// Movie Play
	public static void sendPostWithMoviePlay(final String url, final StbMoviePlayRequest entity)
			throws SiDCException, Exception {

		final APIRequest enity = new APIRequest(entity);
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	// tv channel change
	public static void sendPostWithTvChannelChange(final String url, final List<String> stbipList,
			final String channelno) throws SiDCException, Exception {

		final APIRequest enity = new APIRequest(new StbOpenTvByChannelNoRequest(stbipList, channelno));
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	// reboot
	public static void sendPostWithRebootSTB(final String url, final List<String> stbipList)
			throws SiDCException, Exception {

		final APIRequest enity = new APIRequest(new StbRebootRequest(stbipList));
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	// 導頁
	public static void sendPostWithRedirectPageSTB(final String url, final List<String> stbipList, final String page)
			throws SiDCException, Exception {

		final APIRequest enity = new APIRequest(new StbRedirectPageRequest(stbipList, page));
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	// check out
	public static void sendPostWithCheckOutSTB(final String url, final List<String> stbipList)
			throws SiDCException, Exception {

		final APIRequest enity = new APIRequest(new StbCheckoutRequest(stbipList));

		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	public static void sendGetTORelay(final String url, final List<String> stbipList) throws SiDCException, Exception {
		for (String stbip : stbipList) {
			httpGet(url + stbip);
		}
	}

	// Get
	public static String httpGet(final String url) throws Exception, SiDCException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		String result = null;
		try {
			HttpResponse response = null;

			try {
				response = httpclient.execute(httpGet);
			} catch (ClientProtocolException e) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to SiDC Server.");
			} catch (IOException e) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to SiDC Server.");
			}

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				throw new SiDCException(SystemStatus.HTTP_404_FAIL, "Fail to connect to API of SiDC Server.");
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_METHOD_NOT_ALLOWED) {
				throw new SiDCException(SystemStatus.HTTP_405_FAIL, "Request is wrong method.");
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
				throw new SiDCException(SystemStatus.HTTP_500_FAIL, "SiDC Server is an internal error.");
			}

			HttpEntity resultEnity = response.getEntity();
			if (resultEnity == null) {
				throw new Exception("Request is empty.");
			}

			InputStream instreams = resultEnity.getContent();
			result = parseStreamToString(instreams);

		} finally {
			httpGet.releaseConnection();
		}
		return result;
	}

	// POST
	public static String httpSendPost(final String url, final StringEntity strEntity) throws Exception {

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).build();
		HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpPost httppost = new HttpPost(url);

		String result = null;

		try {
			httppost.setEntity(strEntity);
			HttpResponse response = null;

			try {
				response = httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to SiDC Server.");
			} catch (IOException e) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to SiDC Server.");
			}

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				throw new SiDCException(SystemStatus.HTTP_404_FAIL, "Fail to connect to API of SiDC Server.");
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_METHOD_NOT_ALLOWED) {
				throw new SiDCException(SystemStatus.HTTP_405_FAIL, "Request is wrong method.");
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
				throw new SiDCException(SystemStatus.HTTP_500_FAIL, "SiDC Server is an internal error.");
			}

			HttpEntity resultEnity = response.getEntity();
			if (resultEnity == null) {
				throw new Exception("Request is empty.");
			}

			InputStream instreams = resultEnity.getContent();
			result = parseStreamToString(instreams);
			// LogAction.getInstance().debug("Response=" + result);

		} finally {
			httppost.releaseConnection();
		}
		return result;
	}

	// POST
	public static String httpSendPost(final String url, final StringEntity strEntity,
			final Map<String, String> headerMap) throws Exception {

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).build();
		HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpPost httppost = new HttpPost(url);

		String result = null;

		try {

			for (Map.Entry<String, String> entity : headerMap.entrySet()) {
				httppost.addHeader(entity.getKey(), entity.getValue());
			}

			httppost.setEntity(strEntity);
			HttpResponse response = null;

			try {
				response = httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to " + url);
			} catch (IOException e) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to " + url);
			}

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to " + url);
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_METHOD_NOT_ALLOWED) {
				throw new SiDCException(SystemStatus.HTTP_405_FAIL, "Request is wrong method.");
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
				throw new SiDCException(SystemStatus.HTTP_500_FAIL, "SiDC Server is an internal error.");
			}

			HttpEntity resultEnity = response.getEntity();
			if (resultEnity == null) {
				throw new Exception("Request is empty.");
			}

			InputStream instreams = resultEnity.getContent();
			result = parseStreamToString(instreams);

		} finally {
			httppost.releaseConnection();
		}
		return result;
	}

	public static void sendMsgToSTB(final String url, final String roomno, final String message)
			throws SiDCException, Exception {
		final APIRequest enity = new APIRequest(new StbMessageRequest(roomno, message));

		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	public static String parseStreamToString(InputStream in) throws Exception {

		ByteArrayOutputStream outStream = null;

		String result = null;
		try {
			outStream = new ByteArrayOutputStream();
			byte[] data = new byte[4096];
			int count = -1;
			while ((count = in.read(data, 0, 4096)) != -1)
				outStream.write(data, 0, count);

			data = null;

			result = new String(outStream.toByteArray(), "UTF-8");

		} finally {
			outStream.flush();
			outStream.close();
		}

		return result;
	}

	// upload photo to sits
	public static void sendPostWithUploadPhoto(final String url, final List<String> folderList, final String type,
			final List<PhotoUploadBean> photoList) throws SiDCException, Exception {
		final PhotoUploadRequest request = new PhotoUploadRequest(folderList, type, photoList);
		final APIRequest enity = new APIRequest(request);
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	// delete photo to sits
	public static void sendPostWithDeletePhoto(final String url, final List<String> folderList, final String type,
			final List<String> photoList) throws SiDCException, Exception {
		final PhotoDeleteRequest request = new PhotoDeleteRequest(type, folderList, photoList);
		final APIRequest enity = new APIRequest(request);
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url, strEntity);
	}

	// 通知 sits列印
	public static void sendPostWithPrint(final String url, final String[] printerName, final String data,
			final String signature) throws SiDCException, Exception {
		final PrinterRequest request = new PrinterRequest(printerName, data, signature);
		final APIRequest enity = new APIRequest(request);
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		httpSendPost(url + PageList.PRINT_DATA, strEntity);
	}

	// fcm
	public static String sendPostWithFcm(final FcmBean entity, final String key) throws SiDCException, Exception {
		final String fcmUrl = UrlUtils.getUrl(SidcUrlName.FCM.toString());

		final Gson gson = new Gson();
		final String json = gson.toJson(entity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Authorization", "key=" + key);

		return httpSendPost(fcmUrl, strEntity, map);
	}

	/**
	 * 從 sits取得 hotel 資訊 ,http post 方式 ,sits版本需要 5.32
	 * 
	 * @param url
	 * @param signature
	 * @return
	 * @throws SiDCException
	 * @throws Exception
	 */
	public static HotelInfoResponse sendPostWithHotelInfo(final String signature) throws SiDCException, Exception {
		final HotelInfoRequest request = new HotelInfoRequest(signature);
		final APIRequest enity = new APIRequest(request);
		final Gson gson = new Gson();
		final String json = gson.toJson(enity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
		final String response = httpSendPost(sitsUrl + PageList.HOTEL_INFO, strEntity);

		final ApidocumentBean message = gson.fromJson(response, ApidocumentBean.class);

		if (message.getApidocument().getStatus() != 0) {
			throw new SiDCException(APIStatus.HTTP_METHOD_FAIL, message.getApidocument().getMessage());
		}

		return gson.fromJson(String.valueOf(message.getApidocument().getData()), HotelInfoResponse.class);
	}
}
