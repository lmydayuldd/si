package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlightStatsBean implements Serializable {
	private static final long serialVersionUID = -1415972186960633221L;
	private List<FlightStatsFlightStatusBean> flightStatuses = new ArrayList<FlightStatsFlightStatusBean>();

	public List<FlightStatsFlightStatusBean> getFlightStatuses() {
		return flightStatuses;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsBean [flightStatuses=");
		builder.append(flightStatuses);
		builder.append("]");
		return builder.toString();
	}

	public FlightStatsBean(List<FlightStatsFlightStatusBean> flightStatuses) {
		super();
		this.flightStatuses = flightStatuses;
	}

}
