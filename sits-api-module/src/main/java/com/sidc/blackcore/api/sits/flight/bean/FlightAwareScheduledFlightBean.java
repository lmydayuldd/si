package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlightAwareScheduledFlightBean implements Serializable {
	private static final long serialVersionUID = -8778627134756290289L;
	private List<FlightAwareFlightInfoBean> flights = new ArrayList<FlightAwareFlightInfoBean>();

	public List<FlightAwareFlightInfoBean> getFlights() {
		return flights;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightAwareScheduledFlightBean [flights=");
		builder.append(flights);
		builder.append("]");
		return builder.toString();
	}

	public FlightAwareScheduledFlightBean(List<FlightAwareFlightInfoBean> flights) {
		super();
		this.flights = flights;
	}

}
