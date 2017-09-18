package com.sidc.tester.api.mobile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.google.gson.Gson;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.SystemStatus;

public class Atest {

	@Test
	public void test() throws Exception {
		final Base64 base64 = new Base64();
		String asd = "abc9635741:085ad03d627544b31014291e6f1a9be79e3c2e02";
		String aaa = base64.encodeToString(asd.getBytes());

		air entity = new air("APW108");

		Gson gson = new Gson();
		final String json = gson.toJson(entity);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");

		String response = httpGet(
				"https://flightxml.flightaware.com/json/FlightXML3/FlightInfoStatus?ident=APW108&howMany=10&offset=0",
				aaa);

		System.out.println(response);
	}

	// Get
	public String httpGet(final String url, final String encoding) throws Exception, SiDCException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		httpGet.setHeader("Authorization", "Basic " + encoding);
		String result = null;

		try {
			response = httpclient.execute(httpGet);

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

		} catch (ClientProtocolException e) {
			throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to SiDC Server.");
		} catch (IOException e) {
			throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "Fail to connect to SiDC Server.");
		} finally {
			httpGet.releaseConnection();
			response.close();
		}
		return result;
	}

	// POST
	public String httpSendPost(final String url, final StringEntity strEntity, final String encoding) throws Exception {

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).build();
		HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpPost httppost = new HttpPost(url);

		String result = null;

		try {
			httppost.setHeader("Authorization", "Basic " + encoding);
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

	public String parseStreamToString(InputStream in) throws Exception {

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

class air {
	private String ident;

	public String getIdent() {
		return ident;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("air [ident=");
		builder.append(ident);
		builder.append("]");
		return builder.toString();
	}

	public air(String ident) {
		super();
		this.ident = ident;
	}

}
