package com.sidc.blackcore.api.ra.response;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.roominfo.bean.DynamicRcuGroupEntity;

public class DynamicRcuInfoEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6528178074813976843L;
	private String roomno;
	private String ip;
	private List<DynamicRcuGroupEntity> catalogues = new ArrayList<DynamicRcuGroupEntity>();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getIp() {
		return ip;
	}

	public List<DynamicRcuGroupEntity> getCatalogues() {
		return catalogues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DynamicRcuInfoEntity [roomno=");
		builder.append(roomno);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", catalogues=");
		builder.append(catalogues);
		builder.append("]");
		return builder.toString();
	}

	public DynamicRcuInfoEntity(String roomno, String ip, List<DynamicRcuGroupEntity> catalogues) {
		super();
		this.roomno = roomno;
		this.ip = ip;
		this.catalogues = catalogues;
	}

}
