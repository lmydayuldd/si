/**
 * 
 */
package com.sidc.rcu.hmi.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.conf.SystemConfiguration;
import com.sidc.rcu.hmi.initial.RcuRoomInfoInitial;
import com.sidc.rcu.hmi.initial.RcuRoomStatusInitial;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.RoomRCUListClient;
import com.sidc.sdk.blackcore.rcu.room.RoomStatusListClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class SystemInitialListener implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(SystemInitialListener.class);
	private final String step = "3";

	public SystemInitialListener() {
		// TODO Auto-generated constructor stub
	}

	public void contextDestroyed(ServletContextEvent context) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent context) {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
		logger.info("1/" + step + ": Start to initial Room RCU.");
		try {
			new SystemConfiguration().initial();

			final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
					.get(CommonDataKey.BLACKCORE_SETTING);

//			String apiRequest = new RoomRCUListClient(blackcoreSetting.getUrl()).execute();
//
//			new RcuRoomStatusInitial(apiRequest).execute();
//			logger.info("2/" + step + ": initial Room RCU success.");

			String apiRequest = new RoomStatusListClient(blackcoreSetting.getUrl()).execute();
			new RcuRoomInfoInitial(apiRequest).execute();
			logger.info("3/" + step + ": initial Room check in(out) info success.");

		} catch (SiDCException e) {
			LogAction.getInstance().error(e.getMessage(), e);
		} catch (Exception e) {
			LogAction.getInstance().error(e.getMessage(), e);
		}
	}

}
