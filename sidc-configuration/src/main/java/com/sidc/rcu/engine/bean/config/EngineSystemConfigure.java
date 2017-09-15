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
public class EngineSystemConfigure implements Serializable {

	private static final long serialVersionUID = 4729105261403006952L;
	private List<EngineSystemSetting> settings;

	@XmlElementWrapper(name = "systems")
	@XmlElement(name = "system")
	public void setSettings(List<EngineSystemSetting> settings) {
		this.settings = settings;
	}

	public List<EngineSystemSetting> getSettings() {
		return settings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EngineSystemConfigure [\n\tsettings=");
		builder.append(settings);
		builder.append("]");
		return builder.toString();
	}

}
