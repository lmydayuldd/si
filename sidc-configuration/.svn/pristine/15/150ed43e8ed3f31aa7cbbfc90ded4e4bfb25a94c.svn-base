package com.sidc.configuration.conf;

import java.io.File;

public interface Env {

	public final static String SIDC_URL_PATH = "/blackcore/url/sidc-url.xml".replace("/", File.separator);

	// 若環境建置於Linux時使用
	public final static String SYSTEM_DEF_PATH = System.getProperty("sidc.config.path").replace("\\", File.separator)
			.replace("/", File.separator);

	// 若環境建置Windows時使用
//	 public final static String SYSTEM_DEF_PATH = System.getProperty("user.dir")
//	 + "/webapps/sidc/config".replace("/", File.separator);

	public final static String DOMAINCONFIGURATION = "DOMAINCONFIGURATION";

	public final static String APP_FCM_KEY_CONFIGUATION_PATH = "/blackcore/fcm/app-key.xml".replace("/",
			File.separator);

	public final static String APP_FCM_KEY_CONFIGURATION = "APPFCMKEYCONFIGURATION";

	public final static String AUTHORIZATION_FUNCTION_CONFIGUATION_PATH = "/blackcore/authorization/auth-function.json"
			.replace("/", File.separator);

	public final static String AUTHORIZATION_FUNCTION_LIST = "AUTHORIZATIONFUNCTIONLIST";

	public final static String PRINT_FORMAT = "/blackcore/printer/printfomat.txt".replace("/", File.separator);

	public final static String HOTEL_INFO = "HOTELINFO";
}
