/**
 * 
 */
package com.sidc.blackcore.api.sits.weather.bean;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class OpenWeatherMapSysBean implements Serializable {
	private static final long serialVersionUID = -7811292131167700754L;
	private String country;
	private String sunrise;
	private String sunset;

	public String getCountry() {
		return country;
	}

	public String getSunrise() {
		return sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpenWeatherMapSysBean [country=");
		builder.append(country);
		builder.append(", sunrise=");
		builder.append(sunrise);
		builder.append(", sunset=");
		builder.append(sunset);
		builder.append("]");
		return builder.toString();
	}

	public OpenWeatherMapSysBean(String country, String sunrise, String sunset) {
		super();
		this.country = country;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}

}
