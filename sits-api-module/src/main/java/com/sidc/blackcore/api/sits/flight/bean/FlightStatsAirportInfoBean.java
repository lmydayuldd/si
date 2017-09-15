package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightStatsAirportInfoBean implements Serializable {
	private static final long serialVersionUID = 3643433355460153602L;
	private String departureTerminal;
	private String departureGate;
	private String arrivalTerminal;
	private String arrivalGate;

	public String getDepartureTerminal() {
		return departureTerminal;
	}

	public String getDepartureGate() {
		return departureGate;
	}

	public String getArrivalTerminal() {
		return arrivalTerminal;
	}

	public String getArrivalGate() {
		return arrivalGate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsAirportInfoBean [departureTerminal=");
		builder.append(departureTerminal);
		builder.append(", departureGate=");
		builder.append(departureGate);
		builder.append(", arrivalTerminal=");
		builder.append(arrivalTerminal);
		builder.append(", arrivalGate=");
		builder.append(arrivalGate);
		builder.append("]");
		return builder.toString();
	}

	public FlightStatsAirportInfoBean(String departureTerminal, String departureGate, String arrivalTerminal,
			String arrivalGate) {
		super();
		this.departureTerminal = departureTerminal;
		this.departureGate = departureGate;
		this.arrivalTerminal = arrivalTerminal;
		this.arrivalGate = arrivalGate;
	}

}
