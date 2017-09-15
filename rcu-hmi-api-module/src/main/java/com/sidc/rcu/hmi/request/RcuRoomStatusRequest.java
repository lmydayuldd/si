package com.sidc.rcu.hmi.request;

import java.io.Serializable;
import java.util.List;

import com.sidc.rcu.hmi.bean.RcuDeviceCatalogueBean;

public class RcuRoomStatusRequest implements Serializable {

	private static final long serialVersionUID = -2699171932577673053L;
	private String roomno;
	private String roomType;
	private List<RcuDeviceCatalogueBean> catalogues;

	public String getRoomno() {
		return roomno;
	}

	public String getRoomType() {
		return roomType;
	}

	public List<RcuDeviceCatalogueBean> getCatalogues() {
		return catalogues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuRoomStatusRequest [roomno=");
		builder.append(roomno);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", catalogues=");
		builder.append(catalogues);
		builder.append("]");
		return builder.toString();
	}

	public RcuRoomStatusRequest(String roomno, String roomType, List<RcuDeviceCatalogueBean> catalogues) {
		super();
		this.roomno = roomno;
		this.roomType = roomType;
		this.catalogues = catalogues;
	}
}
