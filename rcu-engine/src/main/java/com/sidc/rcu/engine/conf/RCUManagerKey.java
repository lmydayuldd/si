package com.sidc.rcu.engine.conf;

public interface RCUManagerKey {

	public final static String LOCAL_ADRESS = "LOCAL_ADDRESS";

	//com.sidc.rcu.manager.server.RABroadcastUDPServer
	public final static String RCU_UDP_SERVER = "RCU_UDP_SERVER";

	//com.sidc.rcu.manager.bean.config.RCURmiServiceConfigure
	public final static String RCU_RMI_REC = "RCU_RECEIVER";

	// com.sidc.rcu.manager.bean.config.RcuManagerConfiguration
	public final static String CONFIG_MAIN_RCU_INITIAL = "MAIN_CONFIGURATION";

	// com.sidc.rcu.manager.bean.SiDCServiceConfigure
	public final static String CONFIG_SIDC_SERVER = "SIDC_SERVER";

	// com.sidc.rcu.manager.bean.EngineSystemSetting
	public final static String ENGINE_SYSTEM_CONFIGURE = "ENGINE_SYSTEM_CONFIG";
		
	// com.sidc.rcu.manager.bean.config.RCUSetting
	public final static String CONFIG_RCU = "ZHONGSHAN";

	// java.lang.Thread
	public final static String CONFIG_RCU_RECE_SERVER_THREAD = "RCU_RECE_SERVER";

}
