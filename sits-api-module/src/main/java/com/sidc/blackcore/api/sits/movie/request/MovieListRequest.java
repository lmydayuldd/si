package com.sidc.blackcore.api.sits.movie.request;

import java.io.Serializable;

public class MovieListRequest implements Serializable {
	private static final long serialVersionUID = 185029853260586776L;
	private String publickey;
	private String privatekey;
	private String hotelid;
	private String langcode;
	private String roomno;

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public String getHotelid() {
		return hotelid;
	}

	public String getLangcode() {
		return langcode;
	}

	public String getRoomno() {
		return roomno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieListRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", hotelid=");
		builder.append(hotelid);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append("]");
		return builder.toString();
	}

	public MovieListRequest(String publickey, String privatekey, String hotelid, String langcode, String roomno) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.hotelid = hotelid;
		this.langcode = langcode;
		this.roomno = roomno;
	}

}
