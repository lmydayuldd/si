package com.sidc.rcu.hmi.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketClient {
	private final String uri = "ws://127.0.0.1:8080/sidc-ra-server/socketindexinfo";
	// private final String urlTest =
	// "ws://10.60.1.251:8080/rcu-hmi/socketindexinfo";
	private Session session;

	public void connect(String uri) throws DeploymentException, IOException, URISyntaxException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		container.connectToServer(this, new URI(uri));
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}

	@OnMessage
	public void onMessage(String message, Session session) {
	}

	public void sendMessage(String message) throws IOException {
		if (!session.isOpen()) {
			return;
		}
		session.getBasicRemote().sendText(message);
	}
}
