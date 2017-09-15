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
public class RcuManagerConfiguration implements Serializable {

	private static final long serialVersionUID = 2136204713127131641L;
	private List<RcuSystem> rcuSystems;
	private String engine;
	private String rmi;
	private String sidcService;
	private String ignite;
	private String allowLocalIPRange;

	@XmlElementWrapper(name = "rcuSystem")
	@XmlElement(name = "rcu")
	public void setRcuSystems(List<RcuSystem> rcuSystems) {
		this.rcuSystems = rcuSystems;
	}

	@XmlElement(name = "rmi")
	public void setRmi(String rmi) {
		this.rmi = rmi;
	}

	@XmlElement(name = "sidc-server")
	public void setSidcService(String sidcService) {
		this.sidcService = sidcService;
	}

	@XmlElement(name = "ignite")
	public void setIgnite(String ignite) {
		this.ignite = ignite;
	}

	@XmlElement(name = "allowServerPrixIp")
	public void setAllowLocalIPRange(String allowLocalIPRange) {
		this.allowLocalIPRange = allowLocalIPRange;
	}

	@XmlElement(name = "engine")
	public void setEngine(String engine) {
		this.engine = engine;
	}

	public List<RcuSystem> getRcuSystems() {
		return rcuSystems;
	}

	public String getRmi() {
		return rmi;
	}

	public String getSidcService() {
		return sidcService;
	}

	public String getIgnite() {
		return ignite;
	}

	public String getAllowLocalIPRange() {
		return allowLocalIPRange;
	}

	public String getEngine() {
		return engine;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuManagerConfiguration [\n\trcuSystems=");
		builder.append(rcuSystems);
		builder.append(", rmi=");
		builder.append(rmi);
		builder.append(", sidcService=");
		builder.append(sidcService);
		builder.append(", ignite=");
		builder.append(ignite);
		builder.append(", allowLocalIPRange=");
		builder.append(allowLocalIPRange);
		builder.append("]");
		return builder.toString();
	}

}
