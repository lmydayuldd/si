/**
 * 
 */
package com.sidc.dao.ra.response;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 * @author Nandin
 *
 */
public class Language implements Serializable {

	private static final long serialVersionUID = -3630661438018749946L;

	@QuerySqlField(index = true)
	private String name;
	@QuerySqlField(index = true)
	private String lang;

	public Language(String name) {
		super();
		this.name = name;
	}

	public Language(String name, String lang) {
		super();
		this.name = name;
		this.lang = lang;
	}

	public String getName() {
		return name;
	}

	public String getLang() {
		return lang;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Language [\n\tname=");
		builder.append(name);
		builder.append(", lang=");
		builder.append(lang);
		builder.append("]");
		return builder.toString();
	}

}
