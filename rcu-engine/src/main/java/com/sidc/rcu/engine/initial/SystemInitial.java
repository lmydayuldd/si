/**
 * 
 */
package com.sidc.rcu.engine.initial;

import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.cache.Cache.Entry;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.request.RoomModuleRequest;
import com.sidc.blackcore.api.sits.room.response.RoomNoListResponse;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.raudp.bean.RoomModuleBean;
import com.sidc.rcu.connector.common.DataKey;
import com.sidc.rcu.connector.rmi.intf.RCUReciverRemote;
import com.sidc.rcu.engine.bean.config.RCURmiServiceConfigure;
import com.sidc.rcu.engine.bean.config.RCUService;
import com.sidc.rcu.engine.bean.config.RCUSetting;
import com.sidc.rcu.engine.bean.config.RcuManagerConfiguration;
import com.sidc.rcu.engine.bean.config.RcuSystem;
import com.sidc.rcu.engine.bean.config.RmiService;
import com.sidc.rcu.engine.bean.config.SiDCServiceConfigure;
import com.sidc.rcu.engine.conf.EngineSystemSettingKey;
import com.sidc.rcu.engine.conf.Env;
import com.sidc.rcu.engine.conf.RCUManagerKey;
import com.sidc.rcu.engine.conf.RcuThirdDeviceType;
import com.sidc.rcu.engine.conf.SettingKeyword;
import com.sidc.rcu.engine.configure.SystemConfiguration;
import com.sidc.rcu.engine.server.RCURemoteServer;
import com.sidc.rcu.engine.utils.ConfigurationLoader;
import com.sidc.sdk.sits.RoomNoListClient;
import com.sidc.sdk.sits.ZhongshanInitialClient;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.net.NetUtils;
import com.sidc.utils.status.APIStatus;
import com.sidc.zhongshan.connector.ZhongshanRCUConnectionManager;

/**
 * @author Nandin
 *
 */
public class SystemInitial {

	private final static String MAIN_CONFIGUATION_PATH = "/rcu-manager/configure-initial.xml".replace("/",
			File.separator);

	private final static int STEP = 7;

	private final static Logger logger = LoggerFactory.getLogger(SystemInitial.class);

	public SystemInitial() {

	}

	public void initial() {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());

		try {
			LogAction.getInstance().info("1/" + STEP + " start to inital configuration");
			initConfigure();

			LogAction.getInstance().info("2/" + STEP + " start to initial Room Module");
			startInitialRCUModule();

			LogAction.getInstance().info("3/" + STEP + " start to Memory Cluster Server");
			startMemoryCluster();

			LogAction.getInstance().info("4/" + STEP + " start to Memory Room Module");
			memoryKeycodeInfo();

			LogAction.getInstance().info("5/" + STEP + " start to inital RMI of Receiver");
			startReceiverServer();

			LogAction.getInstance().info("6/" + STEP + " start to UDP Server to broadcast to kinds of platform");
			startRCUServer();

			LogAction.getInstance().info("7/" + STEP + " start to a pool of RCU Sender Server");
			startRCUConnectionPool();

		} catch (Exception ex) {
			LogAction.getInstance().error(ex.getMessage(), ex);
		} finally {
			LogAction.getInstance().writeRecord(APIStatus.SUCCESS, APIStatus.MSG_SUCCESS);
		}
	}

	private void startInitialRCUModule() throws IOException, SiDCException, Exception {
		RCUSetting zhongshanSetting = (RCUSetting) DataCenter.getInstance().get(RCUManagerKey.CONFIG_RCU);
//		File file = new File(Env.SYSTEM_DEF_PATH + zhongshanSetting.getModulePath());
		File file = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\zhongshan\\room-module-tester.json");
		String json = FileUtils.readFileToString(file, CharEncoding.UTF_8);

		List<RoomModuleBean> roomModuleBeans = new Gson().fromJson(json, new TypeToken<List<RoomModuleBean>>() {
			private static final long serialVersionUID = 6442998482620820455L;
		}.getType());

		/*
		 * to initialization of RCU module to Blackcore
		 */
		RoomModuleRequest request = new RoomModuleRequest(roomModuleBeans);
		SiDCServiceConfigure siDCServiceConfigure = (SiDCServiceConfigure) DataCenter.getInstance()
				.get(RCUManagerKey.CONFIG_SIDC_SERVER);
		
		Gson gson = new Gson ();
		System.out.println(gson.toJsonTree(request));

		new ZhongshanInitialClient<Object>("http://10.60.1.251:7056/blackcore", request).execute();

	}

	/*
	 * to share RCU module to zhongshan or other's RCU System in memory
	 */
	private void memoryKeycodeInfo() {
		Map<String, RoomRcuEnity> keycodes = new HashMap<String, RoomRcuEnity>();

		Ignite ignite = Ignition.ignite();
		IgniteCache<String, RoomRcuEnity> roomRCUStatus = ignite.getOrCreateCache("RoomRCUStatus");

		Iterator<Entry<String, RoomRcuEnity>> it = roomRCUStatus.iterator();
		while (it.hasNext()) {
			Entry<String, RoomRcuEnity> entry = it.next();
			keycodes.put(entry.getKey(), entry.getValue());
		}

		DataCenter.getInstance().put(DataKey.RCU_KEYCODE_MODULE, keycodes);
	}

	@SuppressWarnings("null")
	private void startRCUConnectionPool() throws SiDCException, Exception {
		SiDCServiceConfigure sidc_server = (SiDCServiceConfigure) DataCenter.getInstance()
				.get(RCUManagerKey.CONFIG_SIDC_SERVER);
		RoomNoListClient client = new RoomNoListClient(sidc_server.getServer());
		RoomNoListResponse roomlist = client.execute();

		String rcuSystem = useRcuSystem();
		if (StringUtils.isBlank(rcuSystem)) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "RCU System is not exist.");
		}

		if (roomlist == null && roomlist.getRooms() == null) {
			return;
		}

		RCUReciverRemote rmiService = ConfigurationLoader.getInstance().findRMIService();

		if (RcuThirdDeviceType.ZS.equals(rcuSystem)) {

			RCUSetting rcuSetting = (RCUSetting) DataCenter.getInstance().get(RCUManagerKey.CONFIG_RCU);
			RCUService rcuService = ConfigurationLoader.getInstance().readConfig(SettingKeyword.RCU,
					rcuSetting.getService());
			String threadPoolSize = ConfigurationLoader.getInstance()
					.findEngineValueByKey(EngineSystemSettingKey.RECEIVER_THREAD_NUMBER);
			ConfigurationLoader.getInstance().startRCUReceiverClient(rcuService.getMainport(), rmiService,
					Integer.parseInt(threadPoolSize));

			new ZhongshanRCUConnectionManager(rcuService.getTarget(), rcuService.getNoticeHostTimer())
					.initial(roomlist.getRooms());
		}
	}

	private String useRcuSystem() {
		RcuManagerConfiguration rcuManagerConfiguration = (RcuManagerConfiguration) DataCenter.getInstance()
				.get(RCUManagerKey.CONFIG_MAIN_RCU_INITIAL);

		for (RcuSystem rcusystem : rcuManagerConfiguration.getRcuSystems()) {
			if (rcusystem.isEnable()) {
				return rcusystem.getName();
			}
		}

		return null;
	}

	private void startMemoryCluster() throws Exception {
		RcuManagerConfiguration config = (RcuManagerConfiguration) DataCenter.getInstance()
				.get(RCUManagerKey.CONFIG_MAIN_RCU_INITIAL);

		Ignition.start(Env.SYSTEM_DEF_PATH + config.getIgnite());

	}

	private void initConfigure() throws Exception {

		try {
			new SystemConfiguration().config(Env.SYSTEM_DEF_PATH + MAIN_CONFIGUATION_PATH);
		} catch (Exception e) {
			LogAction.getInstance().error(e.getMessage(), e);
			System.exit(1);
			throw e;
		}
	}

	private void startReceiverServer() throws SiDCException {
		RCURmiServiceConfigure rmiServies = (RCURmiServiceConfigure) DataCenter.getInstance()
				.get(RCUManagerKey.RCU_RMI_REC);

		for (RmiService service : rmiServies.getServices()) {

			if (service.getName().equalsIgnoreCase(SettingKeyword.RCU)) {
				RCURemoteServer obj = null;
				try {
					obj = new RCURemoteServer(service.getPort());
				} catch (Exception e) {
					throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
				}

				try {
					Registry registry = LocateRegistry.createRegistry(service.getPort());
					if (StringUtils.isBlank(service.getRmiInterface().getClassname().trim())) {
						continue;
					}

					registry.rebind(service.getRmiInterface().getClassname().trim(), obj);
				} catch (Exception e) {
					throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
				}
			}

		}

	}

	public void startRCUServer() throws Exception {

		RcuManagerConfiguration config = (RcuManagerConfiguration) DataCenter.getInstance()
				.get(RCUManagerKey.CONFIG_MAIN_RCU_INITIAL);

		String ipAllowLocalAddress = null;
		ipAllowLocalAddress = NetUtils.findLocalAllowIP(config.getAllowLocalIPRange());

		if (!StringUtils.isBlank(ipAllowLocalAddress)) {
			DataCenter.getInstance().put(RCUManagerKey.LOCAL_ADRESS, ipAllowLocalAddress);
		}

	}

}
