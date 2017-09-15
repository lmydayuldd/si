package com.sidc.rcu.hmi.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;

import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.bean.RcuBulbInfoBean;
import com.sidc.rcu.hmi.bean.RcuHvacInfoBean;
import com.sidc.rcu.hmi.bean.RcuRoomInfoBean;
import com.sidc.rcu.hmi.bean.RcuServiceInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.rcu.hmi.udp.intf.RcuCommandIntf;
import com.sidc.rcu.hmi.websocket.bean.RoomControlCatalogueWebsocketBean;
import com.sidc.rcu.hmi.websocket.bean.RoomControlCommandBean;
import com.sidc.rcu.hmi.websocket.bean.RoomControlDevicesWebsocketBean;
import com.sidc.rcu.hmi.websocket.bean.RoomControlWebsocketBean;
import com.sidc.rcu.hmi.websocket.configurator.WebSocketEndPointConfigurator;
import com.sidc.sdk.blackcore.rcu.command.RcuCommandClient;
import com.sidc.sdk.blackcore.rcu.command.RcuHvacCommandClient;
import com.sidc.utils.log.RcuReceiverLog;

/**
 * 
 * DEMO
 *
 */
@ServerEndpoint(value = "/socketrcucommand", configurator = WebSocketEndPointConfigurator.class)
public class RcuCommandWebsocket {
	
	private List<Session> userSessions = Collections.synchronizedList(new ArrayList<Session>());

	@OnOpen
	public void onOpen(Session userSession) {
		synchronized (userSessions) {
			userSessions.add(userSession);
		}
	}

	@OnError
	public void error(Session session, Throwable t) throws IOException {
		session.close();
		synchronized (userSessions) {
			userSessions.remove(session);
		}
	}

	@OnClose
	public void onClose(Session userSession) throws IOException {
		userSession.close();
		synchronized (userSessions) {
			userSessions.remove(userSession);
		}
	}

	@OnMessage
	public void onMessage(final String message, Session userSession) throws Exception {

		if (!StringUtils.isBlank(message)) {
			sendCommand(message);
		}

		broadcast(UDPParser.getInstance().toJson(dataProcess()));
	}

	@SuppressWarnings("unchecked")
	private List<RoomControlWebsocketBean> dataProcess() {
		final HashMap<String, RcuRoomInfoBean> roomMap = (HashMap<String, RcuRoomInfoBean>) DataCenter.getInstance()
				.get(CommonDataKey.RCU_ROOM_INFO);

		final HashMap<String, List<RcuBulbInfoBean>> bulbMap = (HashMap<String, List<RcuBulbInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_BULB_INFO);

		final HashMap<String, List<RcuServiceInfoBean>> serviceMap = (HashMap<String, List<RcuServiceInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_SERVICE_INFO);

		final HashMap<String, List<RcuHvacInfoBean>> hvacMap = (HashMap<String, List<RcuHvacInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_HVAC_INFO);

		final List<RoomControlWebsocketBean> list = new ArrayList<RoomControlWebsocketBean>();

		for (final RcuRoomInfoBean entity : roomMap.values()) {

			final List<RoomControlCatalogueWebsocketBean> catalogueList = new ArrayList<RoomControlCatalogueWebsocketBean>();

			if (!bulbMap.get(entity.getRoomNo()).isEmpty()) {
				catalogueList.add(bulbProcess(bulbMap.get(entity.getRoomNo())));
			}

			if (!serviceMap.get(entity.getRoomNo()).isEmpty()) {
				catalogueList.add(serviceProcess(serviceMap.get(entity.getRoomNo())));
			}

			if (!hvacMap.get(entity.getRoomNo()).isEmpty()) {
				catalogueList.add(hvacProcess(hvacMap.get(entity.getRoomNo())));
			}

			list.add(new RoomControlWebsocketBean(entity.getRoomNo(), catalogueList));
		}

		return list;
	}

	private void broadcast(final String msg) throws IOException {
		if (userSessions == null || userSessions.isEmpty()) {
			return;
		}

		synchronized (userSessions) {
			for (Session s : userSessions) {
				if (!s.isOpen())
					continue;
				s.getBasicRemote().sendText(msg);
			}
		}
	}

	private RoomControlCatalogueWebsocketBean bulbProcess(final List<RcuBulbInfoBean> bulbList) {
		if (bulbList.isEmpty()) {
			return null;
		}

		final List<RoomControlDevicesWebsocketBean> list = new ArrayList<RoomControlDevicesWebsocketBean>();

		for (final RcuBulbInfoBean bulbEntity : bulbList) {
			String status = "0";
			if (!StringUtils.isBlank(bulbEntity.getStatus())) {
				status = bulbEntity.getStatus();
			}
			list.add(new RoomControlDevicesWebsocketBean(bulbEntity.getKeycode(), status));
		}

		return new RoomControlCatalogueWebsocketBean("BULB", list);
	}

	private RoomControlCatalogueWebsocketBean serviceProcess(final List<RcuServiceInfoBean> serviceList) {

		if (serviceList.isEmpty()) {
			return null;
		}

		final List<RoomControlDevicesWebsocketBean> list = new ArrayList<RoomControlDevicesWebsocketBean>();

		for (final RcuServiceInfoBean serviceEntity : serviceList) {
			String status = "0";
			if (!StringUtils.isBlank(serviceEntity.getStatus())) {
				status = serviceEntity.getStatus();
			}
			list.add(new RoomControlDevicesWebsocketBean(serviceEntity.getKeycode(), status));
		}

		return new RoomControlCatalogueWebsocketBean("SERVICE", list);
	}

	private RoomControlCatalogueWebsocketBean hvacProcess(final List<RcuHvacInfoBean> hvacList) {
		if (hvacList.isEmpty()) {
			return null;
		}

		final List<RoomControlDevicesWebsocketBean> list = new ArrayList<RoomControlDevicesWebsocketBean>();

		final RcuHvacInfoBean hvacEntity = hvacList.get(0);

		String status = "0";
		if (!StringUtils.isBlank(hvacEntity.getStatus())) {
			status = hvacEntity.getStatus();
		}

		list.add(new RoomControlDevicesWebsocketBean(hvacEntity.getKeycode(), status, hvacEntity.getSpeed(),
				hvacEntity.getTemperature()));

		return new RoomControlCatalogueWebsocketBean("AIR-CONDITION", list);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void sendCommand(final String request) throws Exception {

		RoomControlCommandBean entity = (RoomControlCommandBean) UDPParser.getInstance().parses(request,
				RoomControlCommandBean.class);

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		if (entity != null && commandCheck(entity)) {
			entity.setUuid(UUID.randomUUID().toString());
			
			new RcuHvacCommandClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(entity))
					.execute();

			new RcuCommandClient(blackcoreSetting.getUrl(), RcuCommandIntf.ASK,
					UDPParser.getInstance().toJsonByContent(entity)).execute();
			
		}

	}

	private boolean commandCheck(final RoomControlCommandBean entity) {
		if (StringUtils.isBlank(entity.getRoomno())) {
			return false;
		}
		if (StringUtils.isBlank(entity.getKeycode())) {
			return false;
		}
		return true;
	}
}
