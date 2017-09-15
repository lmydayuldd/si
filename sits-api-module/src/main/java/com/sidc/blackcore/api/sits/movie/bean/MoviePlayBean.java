package com.sidc.blackcore.api.sits.movie.bean;

import java.io.Serializable;

public class MoviePlayBean implements Serializable {
	private static final long serialVersionUID = -4556597563111287520L;
	private String filepath;
	private String langfilepath;
	private String categoryid;

	public String getFilepath() {
		return filepath;
	}

	public String getLangfilepath() {
		return langfilepath;
	}

	public String getCategoryid() {
		return categoryid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoviePlayBean [filepath=");
		builder.append(filepath);
		builder.append(", langfilepath=");
		builder.append(langfilepath);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append("]");
		return builder.toString();
	}

	public MoviePlayBean(String filepath, String langfilepath, String categoryid) {
		super();
		this.filepath = filepath;
		this.langfilepath = langfilepath;
		this.categoryid = categoryid;
	}

}
