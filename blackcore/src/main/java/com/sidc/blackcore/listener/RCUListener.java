/**
 * 
 */
package com.sidc.blackcore.listener;

import java.io.File;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.ignite.Ignition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.blackcore.bean.configuration.BlackcoreConfiguration;
import com.sidc.configuration.Configuration;
import com.sidc.configuration.SETTING;
import com.sidc.configuration.blackcore.RCUServiceConfiguration;
import com.sidc.configuration.conf.Env;
import com.sidc.dao.ra.manager.RCUDeviceManager;
import com.sidc.dao.ra.response.RcuModule;
import com.sidc.ra.logical.common.CommonDataKey;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.net.NetUtils;

/**
 * @author Nandin
 *
 */
public class RCUListener implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(RCUListener.class);

	public RCUListener() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

		/*
		 * close ignite service
		 */
		Ignition.stop(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent context) {

		RCUServiceConfiguration rcuConfig = null;
		try {
			BlackcoreConfiguration configure = (BlackcoreConfiguration) DataCenter.getInstance()
					.get(SETTING.CONFIGURATION);
			rcuConfig = Configuration.readRCUServiceConfiguration(new File(Env.SYSTEM_DEF_PATH + configure.getRcu()));
			DataCenter.getInstance().put(SETTING.RCU_SERVICE_CONFIG, rcuConfig);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		if (rcuConfig == null || !rcuConfig.isEnable()) {
			logger.info("RCU Service is disable.");
			return;
		}

		logger.info("1/3 : Start to initial IP for RCU Connection");
		String ipAllowLocalAddress;
		try {
			ipAllowLocalAddress = NetUtils.findLocalAllowIP("10.60.");

			if (!StringUtils.isBlank(ipAllowLocalAddress)) {
				DataCenter.getInstance().put(CommonDataKey.RCU_MANAGER_ADRESS, ipAllowLocalAddress);
			}

		} catch (SocketException e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("2/3 : Start to initial RCU Device Data");
		try {
			List<RcuModule> list = RCUDeviceManager.getInstance().listRcuDevice();
			DataCenter.getInstance().put(CommonDataKey.DEVICE_LIST, list);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("3/3 : Start to initial memory cluster");
		BlackcoreConfiguration config = (BlackcoreConfiguration) DataCenter.getInstance().get(SETTING.CONFIGURATION);
		Ignition.start(Env.SYSTEM_DEF_PATH + config.getIgnite());

	}
	
}
