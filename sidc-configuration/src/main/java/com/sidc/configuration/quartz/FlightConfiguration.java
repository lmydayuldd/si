/**
 * 
 */
package com.sidc.configuration.quartz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Joe
 *
 */
@SuppressWarnings("restriction")
@XmlRootElement(name = "configuration")
public class FlightConfiguration implements java.io.Serializable {
	private static final long serialVersionUID = 3957840956144735166L;
	private List<FlightSourceConfiguration> sources = new ArrayList<FlightSourceConfiguration>();

	@XmlElementWrapper(name = "sources")
	@XmlElement(name = "source")
	public List<FlightSourceConfiguration> getSources() {
		return sources;
	}

	public void setSources(List<FlightSourceConfiguration> sources) {
		this.sources = sources;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightConfiguration [sources=");
		builder.append(sources);
		builder.append("]");
		return builder.toString();
	}

}
