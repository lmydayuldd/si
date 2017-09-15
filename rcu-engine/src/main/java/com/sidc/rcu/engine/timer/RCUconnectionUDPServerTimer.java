/**
 * 
 */
package com.sidc.rcu.engine.timer;

import java.util.TimerTask;

import com.sidc.rcu.engine.bean.config.RCUService;
import com.sidc.rcu.engine.bean.config.RCUSetting;
import com.sidc.rcu.engine.conf.RCUManagerKey;
import com.sidc.rcu.engine.conf.SettingKeyword;
import com.sidc.rcu.engine.utils.ConfigurationLoader;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
public class RCUconnectionUDPServerTimer extends TimerTask {

	/**
	 * 
	 */
	public RCUconnectionUDPServerTimer() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		try {
			Thread receiverThread = (Thread) DataCenter.getInstance().get(RCUManagerKey.CONFIG_RCU_RECE_SERVER_THREAD);

			if (!receiverThread.isAlive() || receiverThread.isInterrupted()) {
				RCUSetting rcuSetting = (RCUSetting) DataCenter.getInstance().get(RCUManagerKey.CONFIG_RCU);
				RCUService rcuService = ConfigurationLoader.getInstance().readConfig(SettingKeyword.RCU,
						rcuSetting.getService());

				String threadPoolSize = ConfigurationLoader.getInstance()
						.findEngineValueByKey("rcu-receiver-thread-name");

				ConfigurationLoader.getInstance().startRCUReceiverClient(rcuService.getMainport(),
						ConfigurationLoader.getInstance().findRMIService(), Integer.parseInt(threadPoolSize));
			}
		} catch (Exception ex) {
			LogAction.getInstance().error(ex.getMessage(), ex);
		}
	}

}
