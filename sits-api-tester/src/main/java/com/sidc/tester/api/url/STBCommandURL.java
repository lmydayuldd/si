/**
 * 
 */
package com.sidc.tester.api.url;

/**
 * @author Nandin
 *
 */
public interface STBCommandURL {

	public final static String STB_REBOOT = "/stb/api/stbreboot.json";

	public final static String STB_REDIRECT = "/stb/api/redirect.json";

	public final static String STB_CHECKOUT = "/stb/api/checkout.json";

	public final static String STB_WIFI_ON = "/stb/api/wifion.json";

	public final static String STB_WIFI_OFF = "/stb/api/wifioff.json";

	public final static String STB_WAKEUP = "/stb/api/wakeup.json";

	public final static String STB_OPEN_TV_BY_CHANNELNO = "/stb/api/opentvbychannelno.json";

	public final static String STB_ASK_VERSIONNO = "/stb/api/askversion.json";

	public final static String STB_REST_ROOM = "/stb/api/resetroom.json";

	public final static String SC_CMD_MSG_ON = "/stb/api/message.json";

	public final static String SC_CMD_MSG_OFF = "/stb/api/messageoff.json";

	public final static String SC_CMD_UPGRADE_FIRMWARE = "/stb/api/upgradefirmware.json";

	public final static String SC_CMD_SETTING_SYSTEM_INI = "/stb/api/systeminitemplate.json";

	public final static String SC_CMD_PROPERTY_SETTING = "/stb/api/propertysetting.json";

	public final static String SC_CMD_BOOT_MODE = "/stb/api/bootmode.json";

	public final static String STB_MOVIE_PLAY = "/stb/api/openmovie.json";
}
