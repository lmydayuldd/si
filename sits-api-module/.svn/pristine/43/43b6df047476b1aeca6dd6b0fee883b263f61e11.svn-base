/**
 * 
 */
package com.sidc.dao.ra.response;

import java.io.Serializable;
import java.util.List;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 * @author Nandin
 *
 */
public class DeviceEnity implements Serializable {

	private static final long serialVersionUID = 5499119584475342502L;
	@QuerySqlField(index = true)
	private String keycode;
	private Serializable appender;
	private List<Language> langs;
	private Serializable condition;

	public DeviceEnity(String keycode, List<Language> langs) {
		super();
		this.keycode = keycode;
		this.langs = langs;
	}

	public DeviceEnity(String keycode, Serializable appender, List<Language> langs) {
		super();
		this.keycode = keycode;
		this.appender = appender;
		this.langs = langs;
	}

	public Serializable getAppender() {
		return appender;
	}

	public void setAppender(Serializable appender) {
		this.appender = appender;
	}


	public Serializable getCondition() {
		return condition;
	}

	public void setCondition(Serializable condition) {
		this.condition = condition;
	}

	public String getKeycode() {
		return keycode;
	}

	public List<Language> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceEnity [\n\tkeycode=");
		builder.append(keycode);
		builder.append(", appender=");
		builder.append(appender);
		builder.append(", langs=");
		builder.append(langs);
		builder.append(", condition=");
		builder.append(condition);
		builder.append("]");
		return builder.toString();
	}

}
