package com.sidc.configuration.quartz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class FlightStatsApiMethod implements Serializable {
	private static final long serialVersionUID = -58637145659215506L;
	private String method;
	private String apiname;
	private String arrival;
	private String departure;
	private List<FlightStatsKeys> queries = new ArrayList<FlightStatsKeys>();

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getApiname() {
		return apiname;
	}

	public void setApiname(String apiname) {
		this.apiname = apiname;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	@XmlElementWrapper(name = "queries")
	@XmlElement(name = "property")
	public List<FlightStatsKeys> getQueries() {
		return queries;
	}

	public void setQueries(List<FlightStatsKeys> queries) {
		this.queries = queries;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsApiMethod [method=");
		builder.append(method);
		builder.append(", apiname=");
		builder.append(apiname);
		builder.append(", arrival=");
		builder.append(arrival);
		builder.append(", departure=");
		builder.append(departure);
		builder.append(", queries=");
		builder.append(queries);
		builder.append("]");
		return builder.toString();
	}

}
