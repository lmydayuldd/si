package com.sidc.rcu.hmi.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.systeminitial.bean.WebsocketInitialBean;
import com.sidc.rcu.hmi.systeminitial.bean.WebsocketInitialServiceBean;
import com.sidc.utils.log.LogAction;

@ClientEndpoint
public class WebSocketClient {
	private final static Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

	private Session session;

	public void connect(String uri) throws DeploymentException, IOException, URISyntaxException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		container.connectToServer(this, new URI(uri));
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		this.session.setMaxIdleTimeout(-1);
	}

	public void onClose() throws IOException {
		if (session != null && session.isOpen()) {
			this.session.close();
			try {
				rereconnect();
			} catch (DeploymentException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@OnError
	public void error(Session session, Throwable e) throws IOException, DeploymentException, URISyntaxException {
		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
		LogAction.getInstance().error("WebSocketClient|Websocket error", e);

		session.close();
		rereconnect();
		LogAction.getInstance().debug("reconnect success.");

	}

	public void sendMessage(String message) throws IOException {
		if (!session.isOpen()) {
			return;
		}
		session.getBasicRemote().sendText(message);
	}
	
	public boolean isOpen(){
		boolean isOpen = false;
		if(this.session.isOpen()){
			isOpen = true;
		}
		return isOpen;
	}

	private void rereconnect() throws DeploymentException, IOException, URISyntaxException {
		WebsocketInitialBean webscoketSetting = (WebsocketInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.WEBSOCKET_SETTING);
		if (webscoketSetting == null) {
			throw new NullPointerException("webscoketSetting");
		}

		// 設定檔
		String path = null;

		for (WebsocketInitialServiceBean entity : webscoketSetting.getSystemWebsocket()) {
			if (entity.getName().equals("index")) {
				path = entity.getPath();
				break;
			}
		}
		final String url = "ws://" + webscoketSetting.getHost() + ":" + webscoketSetting.getPort() + "/"
				+ webscoketSetting.getServiceName() + "/" + path;

		this.connect(url);
	}
}
