package com.sidc.rcu.hmi.websocket.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomControlWebsocketBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2360015821476659284L;
	private String roomNo;
	private List<RoomControlCatalogueWebsocketBean> catalogueList = new ArrayList<RoomControlCatalogueWebsocketBean>();

	public String getRoomNo() {
		return roomNo;
	}

	public List<RoomControlCatalogueWebsocketBean> getCatalogueList() {
		return catalogueList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomControlWebsocketBean [roomNo=");
		builder.append(roomNo);
		builder.append(", catalogueList=");
		builder.append(catalogueList);
		builder.append("]");
		return builder.toString();
	}

	public RoomControlWebsocketBean(String roomNo, List<RoomControlCatalogueWebsocketBean> catalogueList) {
		super();
		this.roomNo = roomNo;
		this.catalogueList = catalogueList;
	}

}
