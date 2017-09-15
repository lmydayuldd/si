package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightStatsDelayInfoBean implements Serializable {
	private static final long serialVersionUID = -6334493531882423744L;
	private long departureGateDelayMinutes;
	private long arrivalGateDelayMinutes;

	public long getDepartureGateDelayMinutes() {
		return departureGateDelayMinutes;
	}

	public long getArrivalGateDelayMinutes() {
		return arrivalGateDelayMinutes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsDelayInfoBean [departureGateDelayMinutes=");
		builder.append(departureGateDelayMinutes);
		builder.append(", arrivalGateDelayMinutes=");
		builder.append(arrivalGateDelayMinutes);
		builder.append("]");
		return builder.toString();
	}

	public FlightStatsDelayInfoBean(long departureGateDelayMinutes, long arrivalGateDelayMinutes) {
		super();
		this.departureGateDelayMinutes = departureGateDelayMinutes;
		this.arrivalGateDelayMinutes = arrivalGateDelayMinutes;
	}

}
