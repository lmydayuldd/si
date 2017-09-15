package com.sidc.blackcore.api.sits.movie.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;

public class MovieCategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8712062688307921586L;
	private String type_id;
	private int sequence;
	private boolean isProtected;
	private List<LangsBean> langs = new ArrayList<LangsBean>();
	private List<MovieBean> movieInfo = new ArrayList<MovieBean>();

	public List<MovieBean> getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(List<MovieBean> movieInfo) {
		this.movieInfo = movieInfo;
	}

	public String getType_id() {
		return type_id;
	}

	public int getSequence() {
		return sequence;
	}

	public boolean getIsProtected() {
		return isProtected;
	}

	public List<LangsBean> getLangs() {
		return langs;
	}

	public void setLangs(List<LangsBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieCategoryBean [type_id=");
		builder.append(type_id);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", isProtected=");
		builder.append(isProtected);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public MovieCategoryBean(String type_id, int sequence, boolean isProtected) {
		super();
		this.type_id = type_id;
		this.sequence = sequence;
		this.isProtected = isProtected;
	}

}
