/**
 * 
 */
package com.sidc.quartz.listener;

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
import com.sidc.configuration.quartz.FlightConfiguration;
import com.sidc.configuration.quartz.FlightSourceConfiguration;
import com.sidc.configuration.quartz.FlightStatsConfiguration;
import com.sidc.quartz.logical.parameter.Env;
import com.sidc.utils.common.DataCenter;

/**
 * @author Allen
 *
 */
public class SystemInitialListener implements ServletContextListener {

	private final static String MAIN_CONFIGUATION_PATH = "/blackcore/configure-initial.xml".replace("/",
			File.separator);

	public final static String FLIGHT_CONFIGUATION_PATH = "/quartz/flight/flight-config.xml".replace("/",
			File.separator);

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
		FlightConfiguration flightConfigure = null;
		try {
			configure = readMainConfiguration(new File(Env.SYSTEM_DEF_PATH + MAIN_CONFIGUATION_PATH));
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

		try {
			flightConfigure = fligthConfiguration(new File(Env.SYSTEM_DEF_PATH + FLIGHT_CONFIGUATION_PATH));

			for (FlightSourceConfiguration entity : flightConfigure.getSources()) {
				if (entity.isEnable()) {
					final FlightStatsConfiguration flightStatsConfigure = fligthStatsConfiguration(
							new File(Env.SYSTEM_DEF_PATH + entity.getPath()));
					DataCenter.getInstance().put(Env.FLIGHT_SOURCE_CONFIG, entity.getName());
					DataCenter.getInstance().put(Env.FLIGHT_STATS_CONFIG, flightStatsConfigure);
					break;
				}
			}

		} catch (ProxoolException e) {
			logger.error(e.getMessage(), e);
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private BlackcoreConfiguration readMainConfiguration(final File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(BlackcoreConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		BlackcoreConfiguration config = (BlackcoreConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;

	}

	private FlightConfiguration fligthConfiguration(final File file) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(FlightConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		FlightConfiguration config = (FlightConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;
	}

	private FlightStatsConfiguration fligthStatsConfiguration(final File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(FlightStatsConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		FlightStatsConfiguration config = (FlightStatsConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;
	}
}
