package com.sidc.quartz.logical.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.SystemStatus;

public class HttpClientUtils {

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
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to " + url + ".");
			} catch (IOException e) {
				throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to " + url + ".");
			}

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				throw new SiDCException(SystemStatus.HTTP_404_FAIL, "Fail to connect to " + url + ".");
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

}
