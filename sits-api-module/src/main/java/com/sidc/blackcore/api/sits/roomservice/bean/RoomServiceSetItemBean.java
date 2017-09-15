package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;

public class RoomServiceSetItemBean implements Serializable {
	private static final long serialVersionUID = -6388067430484263575L;
	private int itemid;
	private int status;
	private int sequence;
	private String name;
	private String description;

	public int getItemid() {
		return itemid;
	}

	public int getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomServiceSetItemBean [itemid=");
		builder.append(itemid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceSetItemBean(int itemid, int status, int sequence, String name, String description) {
		super();
		this.itemid = itemid;
		this.status = status;
		this.sequence = sequence;
		this.name = name;
		this.description = description;
	}

}
