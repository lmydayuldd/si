/**
 * 
 */
package com.sidc.blackcore.api.sits.room.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nandin
 *
 */
public class RoomNoListResponse implements Serializable {

	private static final long serialVersionUID = 7578209815618091258L;
	private List<String> rooms;

	public RoomNoListResponse(List<String> rooms) {
		super();
		this.rooms = rooms;
	}

	public List<String> getRooms() {
		return rooms;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomNoList [\n\trooms=");
		builder.append(rooms);
		builder.append("]");
		return builder.toString();
	}

}
