/**
 * 
 */
package com.sidc.zhongshan.connector;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.common.RCUKeycode;
import com.sidc.rcu.connector.connector.RcuConnector;
import com.sidc.rcu.connector.intf.RCUStartFacet;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

/**
 * @author Nandin
 *
 */
public class ZhongshanRCUConnectionManager implements RCUStartFacet {

	private int targetPort;
	private int heartbeatTimer;

	public ZhongshanRCUConnectionManager(int targetPort, int heartbeatTimer) {
		this.targetPort = targetPort;
		this.heartbeatTimer = heartbeatTimer;
	}

	public void initial(final List<String> rooms) {

		broadcastRCUReceiverHost(targetPort, heartbeatTimer);

		askRCUStatus(rooms, targetPort);

	}

	private void broadcastRCUReceiverHost(final int targetPort, final int noticeTimer) {

		final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				RcuConnector connector = null;
				try {
					connector = new RCUInitialConnector(targetPort);
					connector.init();

					connector.send(null);
				} catch (Exception e1) {
					// do nothing
				} finally {
					try {
						if (connector != null) {
							connector.close();
						}
					} catch (SiDCException e) {
						// do nothing
					}
				}
			}
		}, 0, noticeTimer * 60, TimeUnit.SECONDS);

	}

	private void askRCUStatus(final List<String> rooms, final int targetPort) {

		int size = rooms.size();
		for (int i = 0; i < size; i++) {
			RcuConnector senderConnector = null;

			final String curr_room = rooms.get(i);
			try {
				senderConnector = new RCUSendConnector(curr_room, targetPort);
				senderConnector.init();

				final String uuid = UUID.randomUUID().toString().replace("-", "");
				senderConnector.send(new RCUCommander(uuid, curr_room, RCUKeycode.ASK));

			} catch (SiDCException e) {
				LogAction.getInstance().info("Room[" + rooms.get(i) + "] is not initialize UDP connection.");
			} catch (Exception e) {
				LogAction.getInstance().info("Room[" + rooms.get(i) + "] is not initialize UDP connection.");
			}
		}

	}

}
