/**
 * 
 */
package tsetaaw.java;

import java.util.List;

import com.derex.cm.stb.api.response.Apidocument;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sidc.blackcore.api.ra.request.RoomModuleRequest;
import com.sidc.blackcore.api.ra.response.RoomInfoEnity;
import com.sidc.blackcore.api.sits.room.response.RoomNoListResponse;
import com.sidc.sdk.blackcore.RoomRCUInfoClient;
import com.sidc.sdk.blackcore.RoomRCUListClient;
import com.sidc.sdk.sits.ZhongshanInitialClient;
import com.sidc.tester.api.http.HttpPostBodyClient;
import com.sidc.tester.api.url.BlackcoreAPIURL;
import com.sidc.tester.api.url.Env;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class BlackcoreRequestAPI {

	private BlackcoreRequestAPI() {
	}

	private static class LazyHolder {
		public static final BlackcoreRequestAPI INSTANCE = new BlackcoreRequestAPI();
	}

	public static BlackcoreRequestAPI getInstance() {
		return LazyHolder.INSTANCE;
	}

	public RoomNoListResponse listRoomNo() throws SiDCException, Exception {

		Apidocument doc = null;

		Gson gson = new Gson();
		String response = new HttpPostBodyClient(Env.BLACKCORE_HOST, BlackcoreAPIURL.ROOM_NO_LIST, null).execute();

		JsonElement json = new JsonParser().parse(response);
		doc = gson.fromJson(json.getAsJsonObject(), Apidocument.class);

		if (doc.getStatus() != APIStatus.SUCCESS) {
			throw new SiDCException(doc.getStatus(), doc.getMessage());
		}

		RoomNoListResponse result = gson.fromJson(gson.toJson(doc.getData()), RoomNoListResponse.class);

		return result;

	}

	public void initZhongshanModule(RoomModuleRequest request) throws SiDCException, Exception {

		new ZhongshanInitialClient(Env.BLACKCORE_HOST, request).execute();

	}

	public String listRoomRCU() throws SiDCException, Exception {

		return new RoomRCUListClient(Env.BLACKCORE_HOST).execute();

	}
	
	public List<RoomInfoEnity> listRoomInfo() throws SiDCException, Exception {

		return new RoomRCUInfoClient(Env.BLACKCORE_HOST).execute();

	}
}
