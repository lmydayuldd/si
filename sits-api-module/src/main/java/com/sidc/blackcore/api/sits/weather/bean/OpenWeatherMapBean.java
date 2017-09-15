/**
 * 
 */
package com.sidc.blackcore.api.sits.weather.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe
 *
 */
public class OpenWeatherMapBean implements Serializable {
	private static final long serialVersionUID = 8424419922389277020L;
	private List<OpenWeatherMapWeatherBean> weather = new ArrayList<OpenWeatherMapWeatherBean>();
	private OpenWeatherMapMainBean main;
	private OpenWeatherMapWindBean wind;
	private OpenWeatherMapSysBean sys;

	public List<OpenWeatherMapWeatherBean> getWeather() {
		return weather;
	}

	public OpenWeatherMapMainBean getMain() {
		return main;
	}

	public OpenWeatherMapWindBean getWind() {
		return wind;
	}

	public OpenWeatherMapSysBean getSys() {
		return sys;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpenWeatherMapBean [weather=");
		builder.append(weather);
		builder.append(", main=");
		builder.append(main);
		builder.append(", wind=");
		builder.append(wind);
		builder.append(", sys=");
		builder.append(sys);
		builder.append("]");
		return builder.toString();
	}

	public OpenWeatherMapBean(List<OpenWeatherMapWeatherBean> weather, OpenWeatherMapMainBean main,
			OpenWeatherMapWindBean wind, OpenWeatherMapSysBean sys) {
		super();
		this.weather = weather;
		this.main = main;
		this.wind = wind;
		this.sys = sys;
	}

}
