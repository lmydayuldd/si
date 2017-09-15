/**
 * 
 */
package com.sidc.blackcore.bean.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nandin
 *
 */
@XmlRootElement(name = "configuration")
public class BlackcoreConfiguration implements java.io.Serializable {

	private static final long serialVersionUID = 6864211078714672338L;
	private String ignite;
	private String proxool;

	@XmlElement(name = "ignite")
	public void setIgnite(String ignite) {
		this.ignite = ignite;
	}

	@XmlElement(name = "proxool")
	public void setProxool(String proxool) {
		this.proxool = proxool;
	}

	public String getIgnite() {
		return ignite;
	}

	public String getProxool() {
		return proxool;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlackcoreConfiguration [\n\tignite=");
		builder.append(ignite);
		builder.append(", proxool=");
		builder.append(proxool);
		builder.append("]");
		return builder.toString();
	}

}
