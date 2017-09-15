package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightAwareAirportBean implements Serializable {
	private static final long serialVersionUID = -1997281203483731667L;
	private String airport;
	private FlightAwareAirportInfoBean airport_info;
	private FlightAwareScheduledFlightBean arrivals;
	private FlightAwareScheduledFlightBean departures;

	public String getAirport() {
		return airport;
	}

	public FlightAwareAirportInfoBean getAirport_info() {
		return airport_info;
	}

	public FlightAwareScheduledFlightBean getArrivals() {
		return arrivals;
	}

	public FlightAwareScheduledFlightBean getDepartures() {
		return departures;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightAwareAirportBean [airport=");
		builder.append(airport);
		builder.append(", airport_info=");
		builder.append(airport_info);
		builder.append(", arrivals=");
		builder.append(arrivals);
		builder.append(", departures=");
		builder.append(departures);
		builder.append("]");
		return builder.toString();
	}

	public FlightAwareAirportBean(String airport, FlightAwareAirportInfoBean airport_info,
			FlightAwareScheduledFlightBean arrivals, FlightAwareScheduledFlightBean departures) {
		super();
		this.airport = airport;
		this.airport_info = airport_info;
		this.arrivals = arrivals;
		this.departures = departures;
	}

}
