/**
 * 
 */
package com.sidc.tester.api;

import com.derex.cm.stb.api.request.APIRequest;
import com.derex.cm.stb.api.request.RoomListStbIPRequest;
import com.derex.cm.stb.api.request.StbAskVersionRequest;
import com.derex.cm.stb.api.request.StbBootModeRequest;
import com.derex.cm.stb.api.request.StbCheckoutRequest;
import com.derex.cm.stb.api.request.StbMessageRequest;
import com.derex.cm.stb.api.request.StbOpenTvByChannelNoRequest;
import com.derex.cm.stb.api.request.StbPropertySettingRequest;
import com.derex.cm.stb.api.request.StbRebootRequest;
import com.derex.cm.stb.api.request.StbRedirectPageRequest;
import com.derex.cm.stb.api.request.StbResetRoomNoRequest;
import com.derex.cm.stb.api.request.StbSystemIniTemplateRequest;
import com.derex.cm.stb.api.request.StbUpgradeFirmwareRequest;
import com.derex.cm.stb.api.request.StbWakeupRequest;
import com.derex.cm.stb.api.request.StbWiFiOffRequest;
import com.derex.cm.stb.api.request.StbWiFiOnRequest;
import com.derex.cm.stb.api.response.Apidocument;
import com.google.gson.Gson;
import com.sidc.tester.api.http.HttpPostBodyClient;
import com.sidc.tester.api.url.Env;
import com.sidc.tester.api.url.STBCommandURL;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class STBCommander {

    private STBCommander() {
    }

    private static class LazyHolder {
	public static final STBCommander INSTANCE = new STBCommander();
    }

    public static STBCommander getInstance() {
	return LazyHolder.INSTANCE;
    }

    public void wifiOn(StbWiFiOnRequest request) throws SiDCException, Exception {

	Apidocument wifi_on_doc = null;

	Gson gson = new Gson();
	try {
	    String wifi_on_response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_WIFI_ON,
		    gson.toJson(new APIRequest(request))).execute();

	    wifi_on_doc = gson.fromJson(wifi_on_response, Apidocument.class);

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	if (wifi_on_doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(wifi_on_doc.getStatus(), wifi_on_doc.getMessage());
	}
    }

    public void wifiOff(StbWiFiOffRequest request) throws SiDCException, Exception {

	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_WIFI_OFF,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}

    }

    public void wakeup(StbWakeupRequest request) throws SiDCException, Exception {

	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_WAKEUP,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}

    }

    public void opentvbychannelno(StbOpenTvByChannelNoRequest request) throws SiDCException, Exception {

	Apidocument doc = null;
	Gson gson = new Gson();

	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_OPEN_TV_BY_CHANNELNO,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void askversionno(StbAskVersionRequest request) throws SiDCException, Exception {

	Apidocument doc = null;
	Gson gson = new Gson();

	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_ASK_VERSIONNO,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void restroom(StbResetRoomNoRequest request) throws SiDCException, Exception {

	Apidocument doc = null;
	Gson gson = new Gson();

	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_REST_ROOM,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void message(StbMessageRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.SC_CMD_MSG_ON,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void messageoff(RoomListStbIPRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.SC_CMD_MSG_OFF,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void upgradefirmware(StbUpgradeFirmwareRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.SC_CMD_UPGRADE_FIRMWARE,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void systeminitemplate(StbSystemIniTemplateRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.SC_CMD_SETTING_SYSTEM_INI,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void propertysetting(StbPropertySettingRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.SC_CMD_PROPERTY_SETTING,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void bootmode(StbBootModeRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.SC_CMD_BOOT_MODE,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void reboot(StbRebootRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_REBOOT,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void redirect(StbRedirectPageRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_REDIRECT,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

    public void checkout(StbCheckoutRequest request) throws SiDCException, Exception {
	Apidocument doc = null;

	Gson gson = new Gson();
	String response = new HttpPostBodyClient(Env.HOST, STBCommandURL.STB_CHECKOUT,
		gson.toJson(new APIRequest(request))).execute();

	doc = gson.fromJson(response, Apidocument.class);

	if (doc.getStatus() != APIStatus.SUCCESS) {
	    throw new SiDCException(doc.getStatus(), doc.getMessage());
	}
    }

}
