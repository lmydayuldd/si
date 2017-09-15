/**
 * 
 */
package com.sidc.dao.ra.response;

import java.util.List;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 * @author Nandin
 *
 */
public class RoomRcuEnity implements java.io.Serializable {

	private static final long serialVersionUID = 5145600682304108289L;

	@QuerySqlField(index = true)
	private String roomno;
	private String roomType;
	private List<DeviceCatalogue> catalogues;

	public RoomRcuEnity(String roomno, String roomType, List<DeviceCatalogue> catalogues) {
		super();
		this.roomno = roomno;
		this.roomType = roomType;
		this.catalogues = catalogues;
	}


	public String getRoomno() {
		return roomno;
	}


	public String getRoomType() {
		return roomType;
	}

	public List<DeviceCatalogue> getCatalogues() {
		return catalogues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomRcuEnity [\n\troomno=");
		builder.append(roomno);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", catalogues=");
		builder.append(catalogues);
		builder.append("]");
		return builder.toString();
	}

}
