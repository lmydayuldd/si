package com.sidc.blackcore.api.sits.movie.bean;

public class MovieListBean {
	private String id;
	private String moviefile;
	private String price;
	private String playtime;
	private boolean bookmark;
	private int bookmarktime;
	private String name;
	private String director;
	private String actor;
	private String introduction;
	private String image;

	public String getId() {
		return id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMoviefile() {
		return moviefile;
	}

	public String getPrice() {
		return price;
	}

	public String getPlaytime() {
		return playtime;
	}

	public String getName() {
		return name;
	}

	public String getDirector() {
		return director;
	}

	public String getActor() {
		return actor;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getImage() {
		return image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean isBookmark() {
		return bookmark;
	}

	public void setBookmark(boolean bookmark) {
		this.bookmark = bookmark;
	}

	public int getBookmarktime() {
		return bookmarktime;
	}

	public void setBookmarktime(int bookmarktime) {
		this.bookmarktime = bookmarktime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieListBean [id=");
		builder.append(id);
		builder.append(", moviefile=");
		builder.append(moviefile);
		builder.append(", price=");
		builder.append(price);
		builder.append(", playtime=");
		builder.append(playtime);
		builder.append(", bookmark=");
		builder.append(bookmark);
		builder.append(", bookmarktime=");
		builder.append(bookmarktime);
		builder.append(", name=");
		builder.append(name);
		builder.append(", director=");
		builder.append(director);
		builder.append(", actor=");
		builder.append(actor);
		builder.append(", introduction=");
		builder.append(introduction);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}

	public MovieListBean(String id, String moviefile, String price, String playtime, String image) {
		super();
		this.id = id;
		this.moviefile = moviefile;
		this.price = price;
		this.playtime = playtime;
		this.image = image;
	}

}
