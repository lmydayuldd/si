package com.sidc.blackcore.api.sits.movie.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.movie.bean.MoviesCatalogueBean;

public class MovieListResponse implements Serializable {
	private static final long serialVersionUID = -8955174804464184411L;
	private String movieserverurl;
	private String previewurl;
	private String currency;
	private boolean ischarge;
	private boolean ispayservice;
	private boolean isadult;
	private List<MoviesCatalogueBean> movieinfolist = new ArrayList<MoviesCatalogueBean>();

	public String getMovieserverurl() {
		return movieserverurl;
	}

	public String getPreviewurl() {
		return previewurl;
	}

	public String getCurrency() {
		return currency;
	}

	public List<MoviesCatalogueBean> getMovieinfolist() {
		return movieinfolist;
	}

	public boolean isCharge() {
		return ischarge;
	}

	public boolean isPayservice() {
		return ispayservice;
	}

	public boolean isAdult() {
		return isadult;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieListResponse [movieserverurl=");
		builder.append(movieserverurl);
		builder.append(", previewurl=");
		builder.append(previewurl);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", ischarge=");
		builder.append(ischarge);
		builder.append(", ispayservice=");
		builder.append(ispayservice);
		builder.append(", isadult=");
		builder.append(isadult);
		builder.append(", movieinfolist=");
		builder.append(movieinfolist);
		builder.append("]");
		return builder.toString();
	}

	public MovieListResponse(String movieserverurl, String previewurl, String currency, boolean ischarge,
			boolean ispayservice, boolean isadult, List<MoviesCatalogueBean> movieinfolist) {
		super();
		this.movieserverurl = movieserverurl;
		this.previewurl = previewurl;
		this.currency = currency;
		this.ischarge = ischarge;
		this.ispayservice = ispayservice;
		this.isadult = isadult;
		this.movieinfolist = movieinfolist;
	}

}
