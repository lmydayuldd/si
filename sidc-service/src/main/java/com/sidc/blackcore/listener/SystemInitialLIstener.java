/**
 * 
 */
package com.sidc.blackcore.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.bean.configuration.BlackcoreConfiguration;
import com.sidc.blackcore.conf.SETTING;
import com.sidc.utils.common.DataCenter;

/**
 * @author Nandin
 *
 */
public class SystemInitialLIstener implements ServletContextListener {

	private final static String MAIN_CONFIGUATION_PATH = "/blackcore/configure-initial.xml".replace("/",
			File.separator);

	private final static Logger logger = LoggerFactory.getLogger(SystemInitialLIstener.class);

	/**
	 * 
	 */
	public SystemInitialLIstener() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent context) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent context) {
		BlackcoreConfiguration configure = null;
		try {
			configure = readMainConfiguration(new File(SETTING.SYSTEM_DEF_PATH + MAIN_CONFIGUATION_PATH));
			DataCenter.getInstance().put(SETTING.CONFIGURATION, configure);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.exit(0);
		}

		try {
			JAXPConfigurator.configure(SETTING.SYSTEM_DEF_PATH + configure.getProxool(), false);
		} catch (ProxoolException e) {
			logger.error(e.getMessage(), e);
			System.exit(0);
		}
	}

	private BlackcoreConfiguration readMainConfiguration(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(BlackcoreConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		BlackcoreConfiguration config = (BlackcoreConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;

	}

}
