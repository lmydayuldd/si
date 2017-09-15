/**
 * 
 */
package com.sidc.rcu.engine.configure;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.sidc.rcu.engine.bean.config.EngineSystemConfigure;
import com.sidc.rcu.engine.bean.config.RCURmiServiceConfigure;
import com.sidc.rcu.engine.bean.config.RCUSetting;
import com.sidc.rcu.engine.bean.config.RcuManagerConfiguration;
import com.sidc.rcu.engine.bean.config.RcuSystem;
import com.sidc.rcu.engine.bean.config.SiDCServiceConfigure;
import com.sidc.rcu.engine.conf.Env;
import com.sidc.rcu.engine.conf.RCUManagerKey;
import com.sidc.utils.common.DataCenter;

/**
 * @author Nandin
 *
 */
public class SystemConfiguration {
	/**
	 * 
	 */
	public SystemConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public void config(final String path) throws Exception {

		RcuManagerConfiguration main_configuration = readRCU(new File(path));
		DataCenter.getInstance().put(RCUManagerKey.CONFIG_MAIN_RCU_INITIAL, main_configuration);

		for (RcuSystem rcuSystem : main_configuration.getRcuSystems()) {
			if (rcuSystem.isEnable()) {
				RCUSetting rcuSetting = readRCUFile(new File(Env.SYSTEM_DEF_PATH + rcuSystem.getPath()));
				DataCenter.getInstance().put(RCUManagerKey.CONFIG_RCU, rcuSetting);
				break;
			}
		}

		File file2 = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\rmi\\rcu-receiver.xml");
//		 read RMI setting
//		File file = new File(Env.SYSTEM_DEF_PATH + main_configuration.getRmi());
		RCURmiServiceConfigure resource = readRMIService(file2);
		if (resource != null) {
			DataCenter.getInstance().put(RCUManagerKey.RCU_RMI_REC, resource);
		}

		File file3 = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\initial\\blackcore.xml");
		// read Blackcore setting
//		File sidc_service_file = new File(Env.SYSTEM_DEF_PATH + main_configuration.getSidcService());
		SiDCServiceConfigure sidc_server_config = readSiDCService(file3);
		DataCenter.getInstance().put(RCUManagerKey.CONFIG_SIDC_SERVER, sidc_server_config);

		
		File file4 = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\engine\\system-config.xml");
		// read Engine System setting
//		File rcu_system_setting = new File(Env.SYSTEM_DEF_PATH + main_configuration.getEngine());
		EngineSystemConfigure rcu_system_config = readEnginSystemSetting(file4);
		DataCenter.getInstance().put(RCUManagerKey.ENGINE_SYSTEM_CONFIGURE, rcu_system_config);

	}

	private RCURmiServiceConfigure readRMIService(File file) throws Exception {

		RCURmiServiceConfigure config = null;
		JAXBContext jaxbContext = JAXBContext.newInstance(RCURmiServiceConfigure.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		config = (RCURmiServiceConfigure) jaxbUnmarshaller.unmarshal(file);

		return config;
	}

	private SiDCServiceConfigure readSiDCService(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(SiDCServiceConfigure.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		SiDCServiceConfigure config = (SiDCServiceConfigure) jaxbUnmarshaller.unmarshal(file);

		return config;
	}
	
	private EngineSystemConfigure readEnginSystemSetting(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(EngineSystemConfigure.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		EngineSystemConfigure config = (EngineSystemConfigure) jaxbUnmarshaller.unmarshal(file);

		return config;
	}

	private RcuManagerConfiguration readRCU(File file) throws Exception {
		File file2 = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\configure-initial.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(RcuManagerConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		RcuManagerConfiguration config = (RcuManagerConfiguration) jaxbUnmarshaller.unmarshal(file2);

		return config;

	}

	private RCUSetting readRCUFile(File file) throws Exception {
		File file2 = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\zhongshan\\initial.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(RCUSetting.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		RCUSetting config = (RCUSetting) jaxbUnmarshaller.unmarshal(file2);

		return config;

	}
}
