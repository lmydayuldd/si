package com.sidc.rcu.engine.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ignite.Ignition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.engine.initial.SystemInitial;

/**
 * @author Nandin
 *
 */
public class SystemInitialListener implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(SystemInitialListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Ignition.stop(false);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		String system_path = System.getProperty("sidc.config.path").replace("\\", File.separator).replace("/",
				File.separator);

		logger.info("System Path=" + system_path);

		new SystemInitial().initial();

	}

}
