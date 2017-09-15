package com.sidc.blackcore.api.sits.tvchannel.request;

import java.io.Serializable;

public class TvChannelListRequest implements Serializable {
	private static final long serialVersionUID = 2570212020745076785L;
	private String roomno;
	private String hotelid;
	private String langcode;

	public String getRoomno() {
		return roomno;
	}

	public String getHotelid() {
		return hotelid;
	}

	public String getLangcode() {
		return langcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TvChannelListRequest [roomno=");
		builder.append(roomno);
		builder.append(", hotelid=");
		builder.append(hotelid);
		builder.append(", langcode=");
		builder.append(langcode);
		builder.append("]");
		return builder.toString();
	}

	public TvChannelListRequest(String roomno, String hotelid, String langcode) {
		super();
		this.roomno = roomno;
		this.hotelid = hotelid;
		this.langcode = langcode;
	}

}
