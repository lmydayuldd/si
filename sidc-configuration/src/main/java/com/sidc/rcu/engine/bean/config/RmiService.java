/**
 * 
 */
package com.sidc.rcu.engine.bean.config;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * @author Nandin
 *
 */
public class RmiService implements Serializable {

	private static final long serialVersionUID = -7411140560107228203L;
	private String name;
	private String host;
	private int port;
	private RmiInterface rmiInterface;

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	@XmlAttribute(name = "host")
	public void setHost(String host) {
		this.host = host;
	}

	@XmlAttribute(name = "port")
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	
	public RmiInterface getRmiInterface() {
		return rmiInterface;
	}
	
	@XmlElement(name = "interface")
	public void setRmiInterface(RmiInterface rmiInterface) {
		this.rmiInterface = rmiInterface;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RmiService [\n\thost=");
		builder.append(host);
		builder.append(", port=");
		builder.append(port);
		builder.append(", interfaces=");
		builder.append(rmiInterface);
		builder.append("]");
		return builder.toString();
	}

}
