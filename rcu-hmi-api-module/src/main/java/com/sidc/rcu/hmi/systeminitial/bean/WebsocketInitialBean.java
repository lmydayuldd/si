package com.sidc.rcu.hmi.systeminitial.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class WebsocketInitialBean implements Serializable {
	private static final long serialVersionUID = 2894462438997066525L;

	private String host;
	private String port;
	private String serviceName;

	private List<WebsocketInitialServiceBean> systemWebsocket;

	public List<WebsocketInitialServiceBean> getSystemWebsocket() {
		return systemWebsocket;
	}

	@XmlElementWrapper(name = "systemWebsocket")
	@XmlElement(name = "service")
	public void setSystemWebsocket(List<WebsocketInitialServiceBean> systemWebsocket) {
		this.systemWebsocket = systemWebsocket;
	}

	public String getHost() {
		return host;
	}

	@XmlElement(name = "host")
	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	@XmlElement(name = "port")
	public void setPort(String port) {
		this.port = port;
	}

	public String getServiceName() {
		return serviceName;
	}

	@XmlElement(name = "serviceName")
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebsocketInitialBean [host=");
		builder.append(host);
		builder.append(", port=");
		builder.append(port);
		builder.append(", serviceName=");
		builder.append(serviceName);
		builder.append(", systemWebsocket=");
		builder.append(systemWebsocket);
		builder.append("]");
		return builder.toString();
	}

}
