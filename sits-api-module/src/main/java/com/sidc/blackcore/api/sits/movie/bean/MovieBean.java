package com.sidc.blackcore.api.sits.movie.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;

public class MovieBean implements Serializable {
	private static final long serialVersionUID = -3344796015707033333L;
	
	private String movie_id;
	private String filename;
	private float price;
	private int playTime;
	private int grade;
	private String normalImg;
	private int sequence;
	private String active_date;
	private boolean isMultiLang;
	private List<LangsBean> gradeLangs = new ArrayList<LangsBean>();
	private List<MovieInfoBean> movieLangs = new ArrayList<MovieInfoBean>();

	public List<LangsBean> getGradeLangs() {
		return gradeLangs;
	}

	public void setGradeLangs(List<LangsBean> gradeLangs) {
		this.gradeLangs = gradeLangs;
	}

	public List<MovieInfoBean> getMovieLangs() {
		return movieLangs;
	}

	public void setMovieLangs(List<MovieInfoBean> movieLangs) {
		this.movieLangs = movieLangs;
	}

	public String getMovie_id() {
		return movie_id;
	}

	public String getFilename() {
		return filename;
	}

	public float getPrice() {
		return price;
	}

	public int getPlayTime() {
		return playTime;
	}

	public int getGrade() {
		return grade;
	}

	public String getNormalImg() {
		return normalImg;
	}

	public int getSequence() {
		return sequence;
	}

	public String getActive_date() {
		return active_date;
	}

	public boolean isMultiLang() {
		return isMultiLang;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieBean [movie_id=");
		builder.append(movie_id);
		builder.append(", filename=");
		builder.append(filename);
		builder.append(", price=");
		builder.append(price);
		builder.append(", playTime=");
		builder.append(playTime);
		builder.append(", grade=");
		builder.append(grade);
		builder.append(", normalImg=");
		builder.append(normalImg);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", active_date=");
		builder.append(active_date);
		builder.append(", isMultiLang=");
		builder.append(isMultiLang);
		builder.append(", gradeLangs=");
		builder.append(gradeLangs);
		builder.append(", movieLangs=");
		builder.append(movieLangs);
		builder.append("]");
		return builder.toString();
	}

	public MovieBean(String movie_id, String filename, float price, int playTime, int grade, String normalImg,
			int sequence, String active_date, boolean isMultiLang) {
		super();
		this.movie_id = movie_id;
		this.filename = filename;
		this.price = price;
		this.playTime = playTime;
		this.grade = grade;
		this.normalImg = normalImg;
		this.sequence = sequence;
		this.active_date = active_date;
		this.isMultiLang = isMultiLang;
	}

}
