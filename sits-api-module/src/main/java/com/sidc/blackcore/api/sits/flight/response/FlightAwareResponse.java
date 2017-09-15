package com.sidc.blackcore.api.sits.flight.response;

import java.io.Serializable;

import com.sidc.blackcore.api.sits.flight.bean.FlightAwareAirportBean;

public class FlightAwareResponse implements Serializable {
	private static final long serialVersionUID = 7173951330548290217L;
	private FlightAwareAirportBean AirportBoardsResult;

	public FlightAwareAirportBean getAirportBoardsResult() {
		return AirportBoardsResult;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightAwareResponse [AirportBoardsResult=");
		builder.append(AirportBoardsResult);
		builder.append("]");
		return builder.toString();
	}

	public FlightAwareResponse(FlightAwareAirportBean airportBoardsResult) {
		super();
		AirportBoardsResult = airportBoardsResult;
	}

}
