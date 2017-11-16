/**
 * 
 */
package com.sidc.blackcore.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.bean.configuration.BlackcoreConfiguration;
import com.sidc.configuration.Configuration;
import com.sidc.configuration.SETTING;
import com.sidc.configuration.blackcore.AllowDomainConfiguration;
import com.sidc.configuration.blackcore.AppFcmKeyConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.conf.Env;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.utils.common.DataCenter;

/**
 * @author Nandin
 *
 */
public class SystemInitialListener implements ServletContextListener {

	private final static String MAIN_CONFIGUATION_PATH = "/blackcore/configure-initial.xml".replace("/",
			File.separator);
	private final static String DOMAIN_CONFIGUATION_PATH = "/blackcore/domain/domain.xml".replace("/", File.separator);

	private final static Logger logger = LoggerFactory.getLogger(SystemInitialListener.class);

	/**
	 * 
	 */
	public SystemInitialListener() {
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
		AllowDomainConfiguration domainConfigure = null;
		SidcUrlsConfiguration sidcConfigure = null;
		AppFcmKeyConfiguration appFcmKeyConfigure = null;
		try {
			configure = Configuration.readMainConfiguration(new File(Env.SYSTEM_DEF_PATH + MAIN_CONFIGUATION_PATH));
			DataCenter.getInstance().put(SETTING.CONFIGURATION, configure);

			domainConfigure = Configuration.readDomainConfiguration(new File(Env.SYSTEM_DEF_PATH + DOMAIN_CONFIGUATION_PATH));
			DataCenter.getInstance().put(Env.DOMAINCONFIGURATION, domainConfigure);

			sidcConfigure = Configuration.readSidcUrlsConfiguration(new File(Env.SYSTEM_DEF_PATH + Env.SIDC_URL_PATH));
			DataCenter.getInstance().put(SidcUrlName.SITS.toString(), sidcConfigure);

			appFcmKeyConfigure = Configuration.readAppFcmKeyConfiguration(
					new File(Env.SYSTEM_DEF_PATH + Env.APP_FCM_KEY_CONFIGUATION_PATH));
			DataCenter.getInstance().put(Env.APP_FCM_KEY_CONFIGURATION, appFcmKeyConfigure);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.exit(0);
		}

		try {
			JAXPConfigurator.configure(Env.SYSTEM_DEF_PATH + configure.getProxool(), false);
		} catch (ProxoolException e) {
			logger.error(e.getMessage(), e);
			System.exit(0);
		}

	}

}
