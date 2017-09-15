/**
 * 
 */
package com.sidc.rcu.engine.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.response.RCUStatusBean;
import com.sidc.configuration.common.key.CommonCatalogueRCUKey;
import com.sidc.dao.ra.response.DeviceCatalogue;
import com.sidc.dao.ra.response.DeviceEnity;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.raudp.bean.PositionBean;
import com.sidc.rcu.connector.bean.receiver.AirConditionReceiver;
import com.sidc.rcu.connector.bean.receiver.BulbReceiver;
import com.sidc.rcu.connector.bean.receiver.CardReceiver;
import com.sidc.rcu.connector.bean.receiver.RCUReceiverInfo;
import com.sidc.rcu.connector.bean.receiver.RCUServiceReceiver;
import com.sidc.rcu.connector.log.RMIReceiverLog;
import com.sidc.rcu.connector.rmi.intf.RCUReciverRemote;
import com.sidc.rcu.engine.bean.config.RCUService;
import com.sidc.rcu.engine.bean.config.SiDCServiceConfigure;
import com.sidc.rcu.engine.conf.RCUManagerKey;
import com.sidc.rcu.engine.conf.SettingKeyword;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.net.UDPClientBroadcast;
import com.sidc.utils.net.UDPConnection;

/**
 * @author Nandin
 *
 */
public class RCURemoteServer extends UnicastRemoteObject implements RCUReciverRemote {

	private final static Logger logger = LoggerFactory.getLogger(RCURemoteServer.class);

	private static final long serialVersionUID = -4963168814802423354L;
	private final static Gson gson = new Gson();
	private RCUService broadcastService;

	public RCURemoteServer(int port) throws RemoteException {
		super(port);

		final SiDCServiceConfigure sidcService = (SiDCServiceConfigure) DataCenter.getInstance()
				.get(RCUManagerKey.CONFIG_SIDC_SERVER);
		broadcastService = readConfig(SettingKeyword.RA, sidcService.getService());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.rcu.connector.connector.RCUReciverRemote#notice()
	 */
	public void notice(Serializable object) throws Exception {

		if (object == null) {
			return;
		}

		RCUReceiverInfo receiver = null;
		if (object instanceof RCUReceiverInfo) {
			receiver = (RCUReceiverInfo) object;

			if (!receiver.getCatalogue().equals(CommonCatalogueRCUKey.HEARTBEAT)) {
				RMIReceiverLog.getInstance().initial(logger, receiver.getUuid());
				RMIReceiverLog.getInstance().setCatalogue(receiver.getCatalogue());
				RMIReceiverLog.getInstance().setRoomNo(receiver.getRoomNo());
				RMIReceiverLog.getInstance().debug("Receiver=" + receiver);

			}
		} else {
			return;
		}

		try {
			if (!receiver.getCatalogue().equals(CommonCatalogueRCUKey.HEARTBEAT)) {
				updateRCUStatus(receiver);
			}
			UDPClientBroadcast broadcast = null;
			try {
				broadcast = new UDPClientBroadcast(new UDPConnection());
				broadcast.send(gson.toJson(receiver).getBytes(), broadcastService.getTarget());
			} catch (Exception ex) {
				// TODO add logger
			} finally {
				broadcast.close();
			}
		} finally {
			if (!receiver.getCatalogue().equals(CommonCatalogueRCUKey.HEARTBEAT)) {
				RMIReceiverLog.getInstance().writeRecord();
			}
		}
	}

	private void updateRCUStatus(RCUReceiverInfo receiver) {

		Ignite ignite = Ignition.ignite();
		IgniteCache<String, RoomRcuEnity> roomRCUStatus = ignite.getOrCreateCache("RoomRCUStatus");
        
		CacheConfiguration<String, RoomRcuEnity> cfg = new CacheConfiguration<>("RoomRCUStatus");
        cfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        
		synchronized (roomRCUStatus) {
			if (roomRCUStatus.get(receiver.getRoomNo()) == null) {
				RMIReceiverLog.getInstance().debug("Room No[" + receiver.getRoomNo() + "] is not found in RCU Status");
				return;
			}

			RoomRcuEnity enity = roomRCUStatus.get(receiver.getRoomNo());

			for (DeviceCatalogue cat : enity.getCatalogues()) {
				if (receiver.getCatalogue().equals(cat.getCatalogue())) {

					for (DeviceEnity device : cat.getDevices()) {
						Serializable result = null;
						String aircondition_address = null;
//						if (CommonCatalogueRCUKey.AIR_CONIDITION.equals(cat.getCatalogue())) {
//							PositionBean pos = (PositionBean) device.getAppender();
//							aircondition_address = String.valueOf(pos.getAddress());
//						}

						result = readDevice(receiver, device.getKeycode(), aircondition_address);

						device.setCondition(result);

						if (result != null) {
							RMIReceiverLog.getInstance().debug(receiver.getRoomNo() + " - device=" + device);

							roomRCUStatus.put(receiver.getRoomNo(), enity);
						}

					}
					continue;
				}

			}
		}

	}

	private Serializable readDevice(final RCUReceiverInfo receiver, final String keycode,
			 String aircondition_address) {

		Serializable serializable = null;
		final List<Serializable> rec_devices = receiver.getData();

		for (Serializable rec_device : rec_devices) {

			if (CommonCatalogueRCUKey.BULB.equals(receiver.getCatalogue())) {
				final BulbReceiver bulb = (BulbReceiver) rec_device;
				if (!StringUtils.isBlank(bulb.getKeycode()) && bulb.getKeycode().equals(keycode)) {
					RMIReceiverLog.getInstance().setContent(gson.toJson(rec_devices));
					serializable = new RCUStatusBean(bulb.getStatus());
					break;
				}
			} /*else if (CommonCatalogueRCUKey.AIR_CONIDITION.equals(receiver.getCatalogue())) {
				final AirConditionReceiver aircondition = (AirConditionReceiver) rec_device;
				aircondition_address = "0";
				if (String.valueOf(aircondition.getPosition()).equals(aircondition_address)) {
					RMIReceiverLog.getInstance().setContent(gson.toJson(aircondition));
					serializable = rec_device;
					break;
				}

			} */else if (CommonCatalogueRCUKey.SERVICE.equals(receiver.getCatalogue())) {
				final RCUServiceReceiver service = (RCUServiceReceiver) rec_device;
				if (!StringUtils.isBlank(service.getKeycode()) && service.getKeycode().equals(keycode)) {
					RMIReceiverLog.getInstance().setContent(gson.toJson(rec_devices));
					serializable = new RCUStatusBean(service.getStatus());
					break;
				}
			} else if (CommonCatalogueRCUKey.CARD.equals(receiver.getCatalogue())) {
				final CardReceiver card = (CardReceiver) rec_device;
				if (!StringUtils.isBlank(card.getKeycode()) && card.getKeycode().equals(keycode)) {
					RMIReceiverLog.getInstance().setContent(gson.toJson(card));
					serializable = rec_device;
					break;
				}

			} else if (CommonCatalogueRCUKey.HEARTBEAT.equals(receiver.getCatalogue())) {
				serializable = rec_device;
				break;
			}
		}

		return serializable;
	}

	public void beat(Serializable object) throws Exception {
		// TODO Auto-generated method stub

	}

	public RCUService readConfig(final String key, List<RCUService> services) {
		RCUService result = null;
		for (RCUService service : services) {
			if (key.equals(service.getName())) {
				result = service;
			}
		}
		return result;
	}
}
