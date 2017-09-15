package com.sidc.rcu.hmi.websocket.configurator;

import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.sidc.rcu.hmi.websocket.RcuCommandWebsocket;
import com.sidc.rcu.hmi.websocket.WebIndexServiceInfoWebsocket;

public class WebSocketEndPointConfigurator extends Configurator {
	private static WebIndexServiceInfoWebsocket InfoWebsocket = new WebIndexServiceInfoWebsocket();
	private static RcuCommandWebsocket RcuCommand = new RcuCommandWebsocket();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {

		switch (endpointClass.getSimpleName()) {
		case "WebIndexServiceInfoWebsocket":
			return (T) InfoWebsocket;
		case "RcuCommandWebsocket":
			return (T) RcuCommand;
		default:
			return null;
		}
	}
}
