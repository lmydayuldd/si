package tsetaaw.java;

import com.derex.cm.stb.api.request.APIRequest;
import com.derex.cm.stb.api.response.Apidocument;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.tester.api.http.HttpPostBodyClient;
import com.sidc.tester.api.url.Env;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class RcuEngineRequestAPI {

	private RcuEngineRequestAPI() {
	}
	
	private static class LazyHolder {
		public static final RcuEngineRequestAPI INSTANCE = new RcuEngineRequestAPI();
	}
	
	public static RcuEngineRequestAPI getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public Object execute(RCUCommander request, String url, String page) throws SiDCException, Exception {
		
		Apidocument doc = null;
		
		Gson gson = new Gson();
		String response = new HttpPostBodyClient(url, page,
				gson.toJson(new APIRequest(request))).execute();
		
		JsonElement json = new JsonParser().parse(response);
		doc = gson.fromJson(json.getAsJsonObject(), Apidocument.class);
		
		if (doc.getStatus() != APIStatus.SUCCESS) {
			throw new SiDCException(doc.getStatus(), doc.getMessage());
		}
		
		return null;
	}
}
