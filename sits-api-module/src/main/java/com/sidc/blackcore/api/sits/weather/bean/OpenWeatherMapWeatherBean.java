/**
 * 
 */
package com.sidc.blackcore.api.sits.weather.bean;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class OpenWeatherMapWeatherBean implements Serializable {
	private static final long serialVersionUID = -7367251345588091159L;
	private String id;
	private String main;
	private String description;
	private String icon;

	public String getId() {
		return id;
	}

	public String getMain() {
		return main;
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return icon;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpenWeatherMapWeatherBean [id=");
		builder.append(id);
		builder.append(", main=");
		builder.append(main);
		builder.append(", description=");
		builder.append(description);
		builder.append(", icon=");
		builder.append(icon);
		builder.append("]");
		return builder.toString();
	}

	public OpenWeatherMapWeatherBean(String id, String main, String description, String icon) {
		super();
		this.id = id;
		this.main = main;
		this.description = description;
		this.icon = icon;
	}

}
