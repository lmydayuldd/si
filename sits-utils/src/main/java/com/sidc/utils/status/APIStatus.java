/**
 * 
 */
package com.sidc.utils.status;

/**
 * @author Nandin
 *
 */
public interface APIStatus {

	public final static int SUCCESS = 0;

	public final static int HTTP_METHOD_FAIL = 101;

	public final static int ILLEGAL_ARGUMENT = 102;

	public final static int LOG_NOT_SETTING = 103;

	public final static int FAIL_AUTHORIZATION = 104;

	public final static int FAIL_AUTHENTICATION = 105;

	public final static int DATA_DOES_NOT_EXIST = 106;

	public final static int DATA_DOES_EXIST = 107;

	public final static int FAIL_OPERATE_PERMISSION = 108;

	public final static int IP_NOT_ALLOWED = 109;

	public final static int STB_TIMEOUT = 1001;

	public final static int STB_NOT_EXIST = 1002;

	public final static int STB_REGISTERED = 1003;

	public final static int STB_CONNECTING = 1004;

	public final static int WIFI_CONNECTION_FAIL = 1005;

	public final static int WIFI_MODIFY_IP_FAIL = 1006;

	public final static int STB_NOT_MATCH_ROOM_IP = 1007;

	public final static int ROOM_NO_SAME_AS_ORG = 1008;

	public final static int MIDDLEWARE_NO_VERSION = 1009;

	public final static int PMS_RESPONSE_FAIL = 1010;

	public final static int WEBSOCKET_CLIENT_FAIL = 1011;

	public final static int WEBSOCKET_SEND_FAIL = 1012;

	public final static int TOKEN_ILLEGAL = 2001;

	public final static int FCM_ERROR = 3001;

	public final static int STAFF_OFFLINE = 4001;

	public final static int QUARTZ_ILLEGAL = 5001;

	public final static int SHOP_INSUFFICIENT_STOCK = 6001;

	public final static int ENCRYPT_FAIL = 7001;

	public final static int GENERAL_ERROR = 9999;

	public final static String MSG_SUCCESS = "success";
}
