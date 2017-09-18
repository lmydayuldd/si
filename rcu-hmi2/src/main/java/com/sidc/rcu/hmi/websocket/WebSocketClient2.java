package com.sidc.rcu.hmi.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketClient2 {

	protected WebSocketContainer container;
	protected Session userSession = null;

	public WebSocketClient2() {
		container = ContainerProvider.getWebSocketContainer();
	}

	public Session getUserSession() {
		return userSession;
	}

	public void setUserSession(Session userSession) {
		this.userSession = userSession;
	}

	public void Connect(String sServer) throws DeploymentException, IOException, URISyntaxException {
		userSession = container.connectToServer(this, new URI(sServer));
	}

	public void sendMessage(String message) {
		try {
			if (!userSession.isOpen()) {
				return;
			}
			for (Session s : userSession.getOpenSessions()) {
				if (!s.isOpen())
					continue;
				s.getBasicRemote().sendText(message);
			}

		} catch (IOException ex) {

		}
	}

	@OnOpen
	public void onOpen(Session session) {
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException {
		for (Session s : userSession.getOpenSessions()) {
			if (session == s) {
				s.close();
			}
		}
	}

	@OnMessage
	public void onMessage(Session session, String msg) throws IOException {

	}

	public void Disconnect() throws IOException {
		System.out.println("close");
		userSession.close();
	}
}
