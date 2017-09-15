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
public class RCURmiServiceConfigure implements Serializable {

	private static final long serialVersionUID = 7766710635500184905L;
	private List<RmiService> services;

	public List<RmiService> getServices() {
		return services;
	}

	@XmlElementWrapper(name = "services")
    @XmlElement(name = "service")
	public void setServices(List<RmiService> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCURmiServiceConfigure [\n\tservices=");
		builder.append(services);
		builder.append("]");
		return builder.toString();
	}

}
