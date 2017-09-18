package com.sidc.rcu.hmi.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.bean.RcuServiceInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.websocket.configurator.WebSocketEndPointConfigurator;
import com.sidc.utils.exception.SiDCException;

@ServerEndpoint(value = "/socketindexinfo", configurator = WebSocketEndPointConfigurator.class)
public class WebIndexServiceInfoWebsocket {
	private List<Session> userSessions = new ArrayList<Session>();

	@OnOpen
	public void onOpen(Session userSession) {
		userSessions.add(userSession);
	}

	@OnClose
	public void onClose(Session userSession) {
		userSessions.remove(userSession);
	}

	@SuppressWarnings("unchecked")
	@OnMessage
	public void onMessage(String message, Session userSession) throws SiDCException, IOException {
		final String[] keycode = { "SOS", "MUR", "DND" };

		final HashMap<String, List<RcuServiceInfoBean>> map = (HashMap<String, List<RcuServiceInfoBean>>) DataCenter
				.getInstance().get(CommonDataKey.RCU_SERVICE_INFO);

		List<RcuServiceInfoBean> list = new ArrayList<RcuServiceInfoBean>();

		for (List<RcuServiceInfoBean> servicelist : map.values()) {
			for (RcuServiceInfoBean entity : servicelist) {
				for (String str : keycode) {
					if (entity.getKeycode().equals(str)) {
						if (entity.getStatus().equals("1")) {
							list.add(entity);
						}
						break;
					}
				}
			}
		}

		for (Session s : userSessions) {
			if (!s.isOpen())
				continue;
			s.getBasicRemote().sendText(UDPParser.getInstance().toJson(list));
		}
	}
}
