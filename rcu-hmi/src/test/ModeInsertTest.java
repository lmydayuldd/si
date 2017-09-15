package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.sidc.rcu.hmi.modesetting.bean.DeviceBean;
import com.sidc.rcu.hmi.modesetting.bean.DeviceSettingBean;
import com.sidc.rcu.hmi.modesetting.request.ModeInsertRequest;
import com.sidc.utils.exception.SiDCException;

public class ModeInsertTest {

	@Test
	public void test() throws SiDCException, Exception {
		List<DeviceBean> devices = new ArrayList<DeviceBean>();

		DeviceSettingBean setting = new DeviceSettingBean("0", null, null, null, null, null);
		DeviceBean devcieBean = new DeviceBean("BATH", setting);
		devices.add(devcieBean);

		setting = new DeviceSettingBean(null, "true", "1", "26", "0", "0");
		devcieBean = new DeviceBean("HVAC-ALL", setting);
		devices.add(devcieBean);

		ModeInsertRequest request = new ModeInsertRequest(1, 2, devices);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://10.60.1.39:8080/rcu-hmi/mode/insert");

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
