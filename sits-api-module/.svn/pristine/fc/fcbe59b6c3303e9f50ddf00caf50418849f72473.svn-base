package com.sidc.blackcore.api.sits.movie.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoviesCatalogueBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1895838465338553586L;
	private String catalogueid;
	private String catalogue;
	private int sequence;
	private boolean isprotected;

	private List<MovieListBean> movieslist = new ArrayList<MovieListBean>();

	public String getCatalogueid() {
		return catalogueid;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public int getSequence() {
		return sequence;
	}

	public boolean isIsprotected() {
		return isprotected;
	}

	public List<MovieListBean> getMovieslist() {
		return movieslist;
	}

	public void setMovieslist(List<MovieListBean> movieslist) {
		this.movieslist = movieslist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoviesCatalogueBean [catalogueid=");
		builder.append(catalogueid);
		builder.append(", catalogue=");
		builder.append(catalogue);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", isprotected=");
		builder.append(isprotected);
		builder.append(", movieslist=");
		builder.append(movieslist);
		builder.append("]");
		return builder.toString();
	}

	public MoviesCatalogueBean(String catalogueid, String catalogue, int sequence, boolean isprotected) {
		super();
		this.catalogueid = catalogueid;
		this.catalogue = catalogue;
		this.sequence = sequence;
		this.isprotected = isprotected;
	}

}
