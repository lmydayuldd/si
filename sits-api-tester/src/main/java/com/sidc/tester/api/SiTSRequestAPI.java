/**
 * 
 */
package com.sidc.tester.api;

import com.derex.cm.stb.api.request.APIRequest;
import com.derex.cm.stb.api.request.RoomListStbIPRequest;
import com.derex.cm.stb.api.response.Apidocument;
import com.derex.cm.stb.api.response.RoomListStbIPResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sidc.tester.api.http.HttpPostBodyClient;
import com.sidc.tester.api.url.Env;
import com.sidc.tester.api.url.SiTSAPIURL;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class SiTSRequestAPI {

	private SiTSRequestAPI() {
	}

	private static class LazyHolder {
		public static final SiTSRequestAPI INSTANCE = new SiTSRequestAPI();
	}

	public static SiTSRequestAPI getInstance() {
		return LazyHolder.INSTANCE;
	}

	public RoomListStbIPResponse listRoomStbStatu(RoomListStbIPRequest request) throws SiDCException, Exception {

		Apidocument doc = null;

		Gson gson = new Gson();
		String response = new HttpPostBodyClient(Env.HOST, SiTSAPIURL.ROOM_STB_STATUS,
				gson.toJson(new APIRequest(request))).execute();

		JsonElement json = new JsonParser().parse(response);
		doc = gson.fromJson(json.getAsJsonObject().get("apidocument"), Apidocument.class);

		if (doc.getStatus() != APIStatus.SUCCESS) {
			throw new SiDCException(doc.getStatus(), doc.getMessage());
		}

		RoomListStbIPResponse result = gson.fromJson(gson.toJson(doc.getData()), RoomListStbIPResponse.class);

		return result;

	}

}
