package com.sidc.blackcore.api.sits.movie.request;

import java.io.Serializable;

public class MoviePlayRequest implements Serializable {
	private static final long serialVersionUID = -6937539582479147664L;
	private String publickey;
	private String privatekey;
	private String langcode;
	private String roomno;
	private String ip;
	private String movieid;
	private boolean isbookmark;

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getLangcode() {
		return langcode;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getIp() {
		return ip;
	}

	public String getMovieid() {
		return movieid;
	}

	public boolean isBookmark() {
		return isbookmark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoviePlayRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", movieid=");
		builder.append(movieid);
		builder.append(", isbookmark=");
		builder.append(isbookmark);
		builder.append("]");
		return builder.toString();
	}

	public MoviePlayRequest(String publickey, String privatekey, String langcode, String roomno, String ip,
			String movieid, boolean isbookmark) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.langcode = langcode;
		this.roomno = roomno;
		this.ip = ip;
		this.movieid = movieid;
		this.isbookmark = isbookmark;
	}

}
