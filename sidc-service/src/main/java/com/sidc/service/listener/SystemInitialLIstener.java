/**
 * 
 */
package com.sidc.service.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.service.conf.SETTING;

/**
 * @author Nandin
 *
 */
public class SystemInitialLIstener implements ServletContextListener {

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

		try {
			String projectPath = context.getServletContext()
					.getRealPath(context.getServletContext().getInitParameter(SETTING.PROXOOL));
			JAXPConfigurator.configure(projectPath, false);
		} catch (ProxoolException e) {
			logger.error(e.getMessage(), e);
			System.exit(0);
		}
	}

}
