/**
 * 
 */
package com.sidc.rcu.engine.utils;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import com.sidc.ra.udp.server.RCUReceiverThread;
import com.sidc.rcu.connector.rmi.intf.RCUReciverRemote;
import com.sidc.rcu.engine.bean.config.EngineSystemConfigure;
import com.sidc.rcu.engine.bean.config.EngineSystemSetting;
import com.sidc.rcu.engine.bean.config.RCURmiServiceConfigure;
import com.sidc.rcu.engine.bean.config.RCUService;
import com.sidc.rcu.engine.bean.config.RmiService;
import com.sidc.rcu.engine.conf.RCUManagerKey;
import com.sidc.rcu.engine.conf.SettingKeyword;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.log.LogAction;
import com.sidc.zhongshan.connector.RCUReceiveConnector;

/**
 * @author Nandin
 *
 */
public class ConfigurationLoader {

	private ConfigurationLoader() {
	}

	private static class LazyHolder {
		public static final ConfigurationLoader INSTANCE = new ConfigurationLoader();
	}

	public static ConfigurationLoader getInstance() {
		return LazyHolder.INSTANCE;
	}

	public RCUService readConfig(final String key, final List<RCUService> services) {
		RCUService result = null;
		for (RCUService service : services) {
			if (key.equalsIgnoreCase(service.getName())) {
				result = service;
				break;
			}
		}
		return result;
	}

	public RCUReciverRemote findRMIService() throws Exception {
		RCUReciverRemote stub = null;
		RCURmiServiceConfigure rmiConfig = (RCURmiServiceConfigure) DataCenter.getInstance()
				.get(RCUManagerKey.RCU_RMI_REC);

		for (RmiService rmiService : rmiConfig.getServices()) {
			if (rmiService.getName().equalsIgnoreCase(SettingKeyword.RCU)) {
				Registry registry = LocateRegistry.getRegistry(rmiService.getHost(), rmiService.getPort());
				stub = (RCUReciverRemote) registry.lookup(rmiService.getRmiInterface().getClassname().trim());
				break;
			}
		}

		return stub;
	}

	public String findEngineValueByKey(String key) {

		EngineSystemConfigure configs = (EngineSystemConfigure) DataCenter.getInstance()
				.get(RCUManagerKey.ENGINE_SYSTEM_CONFIGURE);

		for (EngineSystemSetting setting : configs.getSettings()) {
			if (setting.getName().equals(key)) {
				return setting.getValue();
			}
		}

		return null;
	}

	public void startRCUReceiverClient(final int receivePort, final RCUReciverRemote stub, final int threadPoolSize) {

		try {
			Thread receiverThread = new RCUReceiverThread(new RCUReceiveConnector(receivePort, stub, threadPoolSize));
			DataCenter.getInstance().put(RCUManagerKey.CONFIG_RCU_RECE_SERVER_THREAD, receiverThread);
			receiverThread.start();
		} catch (Exception ex) {
			LogAction.getInstance().error(ex.getMessage(), ex);
		}

	}
}
