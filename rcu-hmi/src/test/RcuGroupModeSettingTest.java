package test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingListRequest;
import com.sidc.utils.exception.SiDCException;

public class RcuGroupModeSettingTest {

	@Test
	public void test() throws SiDCException, Exception {

		ModeSettingListRequest request = new ModeSettingListRequest(1, 2);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:8080/rcu-hmi/group/mode/setting");

		// APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(request);

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

}
