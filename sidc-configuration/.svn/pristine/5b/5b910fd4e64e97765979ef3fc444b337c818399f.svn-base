/**
 * 
 */
package com.sidc.rcu.engine.bean.config;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nandin
 *
 */
@XmlRootElement(name = "configuration")
public class SiDCServiceConfigure implements Serializable {

	private static final long serialVersionUID = 7766710635500184905L;
	private String server;
	private List<RCUService> services;

	public String getServer() {
		return server;
	}

	@XmlElement(name = "url")
	public void setServer(String server) {
		this.server = server;
	}

	@XmlElementWrapper(name = "servers")
	@XmlElement(name = "server")
	public void setService(List<RCUService> services) {
		this.services = services;
	}

	public List<RCUService> getService() {
		return services;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SiDCServiceConfigure [\n\tserver=");
		builder.append(server);
		builder.append("]");
		return builder.toString();
	}

}
