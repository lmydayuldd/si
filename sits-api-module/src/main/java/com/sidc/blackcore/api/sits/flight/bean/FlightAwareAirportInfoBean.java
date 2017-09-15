package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightAwareAirportInfoBean implements Serializable {
	private static final long serialVersionUID = 4052088190648627312L;
	private String airport_code;
	private String name;
	private float elevation;
	private String city;
	private float longitude;
	private float latitude;
	private String timezone;
	private String country_code;
	private String wiki_url;

	public String getAirport_code() {
		return airport_code;
	}

	public String getName() {
		return name;
	}

	public float getElevation() {
		return elevation;
	}

	public String getCity() {
		return city;
	}

	public float getLongitude() {
		return longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getCountry_code() {
		return country_code;
	}

	public String getWiki_url() {
		return wiki_url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightAwareAirportInfoBean [airport_code=");
		builder.append(airport_code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", elevation=");
		builder.append(elevation);
		builder.append(", city=");
		builder.append(city);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", timezone=");
		builder.append(timezone);
		builder.append(", country_code=");
		builder.append(country_code);
		builder.append(", wiki_url=");
		builder.append(wiki_url);
		builder.append("]");
		return builder.toString();
	}

	public FlightAwareAirportInfoBean(String airport_code, String name, float elevation, String city, float longitude,
			float latitude, String timezone, String country_code, String wiki_url) {
		super();
		this.airport_code = airport_code;
		this.name = name;
		this.elevation = elevation;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timezone = timezone;
		this.country_code = country_code;
		this.wiki_url = wiki_url;
	}

}
