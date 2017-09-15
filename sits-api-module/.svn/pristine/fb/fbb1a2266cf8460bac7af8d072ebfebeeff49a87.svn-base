/**
 * 
 */
package com.sidc.blackcore.api.sits.weather.bean;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class OpenWeatherMapMainBean implements Serializable {
	private static final long serialVersionUID = -2101146631947451232L;
	private float temp;
	private float pressure;
	private float humidity;
	private float temp_min;
	private float temp_max;
	private float sea_level;
	private float grnd_level;

	public float getTemp() {
		return temp;
	}

	public float getPressure() {
		return pressure;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getTemp_min() {
		return temp_min;
	}

	public float getTemp_max() {
		return temp_max;
	}

	public float getSea_level() {
		return sea_level;
	}

	public float getGrnd_level() {
		return grnd_level;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpenWeatherMapMainBean [temp=");
		builder.append(temp);
		builder.append(", pressure=");
		builder.append(pressure);
		builder.append(", humidity=");
		builder.append(humidity);
		builder.append(", temp_min=");
		builder.append(temp_min);
		builder.append(", temp_max=");
		builder.append(temp_max);
		builder.append(", sea_level=");
		builder.append(sea_level);
		builder.append(", grnd_level=");
		builder.append(grnd_level);
		builder.append("]");
		return builder.toString();
	}

	public OpenWeatherMapMainBean(float temp, float pressure, float humidity, float temp_min, float temp_max,
			float sea_level, float grnd_level) {
		super();
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.sea_level = sea_level;
		this.grnd_level = grnd_level;
	}

}
