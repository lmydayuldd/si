/**
 * 
 */
package com.sidc.tester.api.http;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Nandin
 *
 */
public class HttpPostBodyClient {

	private final static String USER_AGENT = "Mozilla/5.0";

	private String host;
	private String url;
	private String body;

	public HttpPostBodyClient(String host, String url, String body) {
		super();
		this.host = host;
		this.url = url;
		this.body = body;
	}

	public String execute() throws Exception {
		return run();
	}

	private String run() throws Exception {

		String result = null;

		CookieHandler.setDefault(new CookieManager());
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost post = new HttpPost(host + url);
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", "application/json");

		if (this.body != null) {
			HttpEntity enity = new ByteArrayEntity(this.body.getBytes(StandardCharsets.UTF_8));
			post.setEntity(enity);
		}

		CloseableHttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new Exception("Connection fail=" + response.getStatusLine().getStatusCode());
		}

		result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);

		return result;
	}
}
