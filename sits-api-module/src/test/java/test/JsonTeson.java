package test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.derex.cm.stb.api.request.APIRequest;
import com.derex.cm.stb.api.request.StbAuthRebootRequest;
import com.derex.cm.stb.api.response.Apidocument;
import com.derex.cm.stb.api.response.STBStatusInfo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonTeson {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {

		String json = "{\"apidocument\":{\"data\":{\"list\":[{\"billno\":\"8abc81b63315d546013315da9fd20001\",\"floor\":4,\"ip\":\"0:0:0:0:0:0:0:1\",\"registed\":true,\"roomno\":\"402\",\"status\":false,\"wifi\":\"10.80.4.2\"},{\"billno\":\"8abc8346277991f7012779cf99830001\",\"floor\":4,\"ip\":\"10.60.4.5\",\"registed\":false,\"roomno\":\"405\",\"status\":false,\"wifi\":\"10.80.4.5\"},{\"billno\":\"402881e73315880d0133158836b60002\",\"floor\":99,\"ip\":\"127.0.0.1\",\"registed\":false,\"roomno\":\"9999\",\"status\":false,\"wifi\":\"10.80.0.1\"}],\"size\":3},\"message\":\"sccess\",\"status\":0,\"time\":1469006947649}}";

		JsonElement apid = new JsonParser().parse(json).getAsJsonObject().get("apidocument");

		Gson gson = new Gson();
		System.out.println(apid.getAsJsonObject().toString());

		Apidocument document = gson.fromJson(apid.getAsJsonObject(), Apidocument.class);
		System.out.println();

		STBStatusInfo result = gson.fromJson(gson.toJson(document.getData()), STBStatusInfo.class);
		System.out.println(document.toString());
	}

	@Test
	public void test2() {

		Gson gson = new Gson();

		String json = "{\"content\":{\"stbip\":\"127.0.0.1\"},\"limit\":10,\"page\":1}";

		APIRequest message = gson.fromJson(json, APIRequest.class);

		System.out.println(message);

		StbAuthRebootRequest res = gson.fromJson(gson.toJson(message.getContent()), StbAuthRebootRequest.class);

		System.out.println(res);
	}

}
