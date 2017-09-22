package com.sidc.rcu.hmi.conf;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.rcu.hmi.systeminitial.bean.WebsocketInitialBean;

public class SystemConfiguration {
	public SystemConfiguration() throws Exception {
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = LoggerFactory.getLogger(SystemConfiguration.class);

	public void initial() throws Exception {
		
		logger.info("SYSTEM_DEF_PATH:" + Env.SYSTEM_DEF_PATH);
		logger.info("HMI_CONFIGUATION_PATH:" + Env.HMI_CONFIGUATION_PATH);
		
		final WebsocketInitialBean websocketSetting = readWebSocketFile(
				new File(Env.SYSTEM_DEF_PATH + Env.HMI_CONFIGUATION_PATH));

		if (websocketSetting == null) {
			throw new NullPointerException("websocketSetting");
		}

		DataCenter.getInstance().put(CommonDataKey.WEBSOCKET_SETTING, websocketSetting);

		final BlackcoreInitialBean blackcoreSetting = readBlackcoreFile(
				new File(Env.SYSTEM_DEF_PATH + Env.BLACKCORE_CONFIGUATION_PATH));

		if (blackcoreSetting == null) {
			throw new NullPointerException("blackcoreSetting");
		}

		DataCenter.getInstance().put(CommonDataKey.BLACKCORE_SETTING, blackcoreSetting);

	}

	private WebsocketInitialBean readWebSocketFile(final File file) throws Exception {
//		File file2 = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\hmi\\websocket.xml");
		final JAXBContext jaxbContext = JAXBContext.newInstance(WebsocketInitialBean.class);

		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		final WebsocketInitialBean config = (WebsocketInitialBean) jaxbUnmarshaller.unmarshal(file);

		return config;
	}

	private BlackcoreInitialBean readBlackcoreFile(final File file) throws Exception {
//		File file2 = new File("D:\\workspace\\blackcore\\rcu-engine\\rcu-manager\\initial\\blackcore.xml");

		final JAXBContext jaxbContext = JAXBContext.newInstance(BlackcoreInitialBean.class);

		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		final BlackcoreInitialBean config = (BlackcoreInitialBean) jaxbUnmarshaller.unmarshal(file);

		return config;
	}
}
