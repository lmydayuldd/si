package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightAwareArrivalOriginAirportBean implements Serializable {
	private static final long serialVersionUID = 5471620616441900324L;
	private String code;
	private String city;
	private String alternate_ident;
	private String airport_name;

	public String getCode() {
		return code;
	}

	public String getCity() {
		return city;
	}

	public String getAlternate_ident() {
		return alternate_ident;
	}

	public String getAirport_name() {
		return airport_name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightAwareArrivalOriginAirportBean [code=");
		builder.append(code);
		builder.append(", city=");
		builder.append(city);
		builder.append(", alternate_ident=");
		builder.append(alternate_ident);
		builder.append(", airport_name=");
		builder.append(airport_name);
		builder.append("]");
		return builder.toString();
	}

	public FlightAwareArrivalOriginAirportBean(String code, String city, String alternate_ident, String airport_name) {
		super();
		this.code = code;
		this.city = city;
		this.alternate_ident = alternate_ident;
		this.airport_name = airport_name;
	}

}
