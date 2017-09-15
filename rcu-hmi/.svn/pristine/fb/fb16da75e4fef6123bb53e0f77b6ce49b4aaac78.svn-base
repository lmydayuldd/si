package com.sidc.rcu.hmi.listener;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sidc.rcu.hmi.timer.UDPServerTimer;

public class MonitorListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		TimerTask timerTask = new UDPServerTimer();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, 1 * 1000);

	}
}
