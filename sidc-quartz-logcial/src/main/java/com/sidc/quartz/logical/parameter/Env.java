package com.sidc.quartz.logical.parameter;

import java.io.File;

public interface Env {

	// 若環境建置於Linux時使用
	public final static String SYSTEM_DEF_PATH = System.getProperty("sidc.config.path").replace("\\", File.separator)
			.replace("/", File.separator);

	// 若環境建置Windows時使用
	// public final static String SYSTEM_DEF_PATH =
	// System.getProperty("user.dir")
	// + "/webapps/sidc/config".replace("/", File.separator);

	public final static String SCHEDULE_HOST = "http://127.0.0.1:3456/quartz";

	public final static String FLIGHT_SOURCE_CONFIG = "FlightSourceConfiguration";

	public final static String FLIGHT_STATS_CONFIG = "FlightStatsConfiguration";

}
