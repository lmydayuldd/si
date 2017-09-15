package com.sidc.rcu.engine.listener;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sidc.rcu.engine.conf.EngineSystemSettingKey;
import com.sidc.rcu.engine.timer.RCUconnectionUDPServerTimer;
import com.sidc.rcu.engine.utils.ConfigurationLoader;

/**
 * @author Nandin
 *
 */
public class MonitorListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		String udp_monitor_timer = ConfigurationLoader.getInstance()
				.findEngineValueByKey(EngineSystemSettingKey.UDP_MONITOR_TIMER);

		TimerTask timerTask = new RCUconnectionUDPServerTimer();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, Integer.parseInt(udp_monitor_timer) * 1000);

	}

}
