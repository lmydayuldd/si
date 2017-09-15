/**
 * 
 */
package com.sidc.configuration.blackcore;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nandin
 *
 */
@SuppressWarnings("restriction")
@XmlRootElement(name = "configuration")
public class RCUServiceConfiguration implements java.io.Serializable {

	private static final long serialVersionUID = 4887342142543458523L;
	private boolean isEnable;

	@XmlElement(name = "enable")
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isEnable() {
		return isEnable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCUServiceConfiguration [\n\tisEnable=");
		builder.append(isEnable);
		builder.append("]");
		return builder.toString();
	}

}
