/**
 * 
 */
package com.sidc.rcu.connector.bean.receiver;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nandin
 *
 */
public class RCUReceiverInfo implements Serializable {

	private static final long serialVersionUID = -3556945394392724896L;

	private String uuid;
	private String roomNo;
	private String Catalogue;
	private List<Serializable> data;

	public RCUReceiverInfo(String uuid, String roomNo, String Catalogue, List<Serializable> data) {
		super();
		this.uuid = uuid;
		this.roomNo = roomNo;
		this.Catalogue = Catalogue;
		this.data = data;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public List<Serializable> getData() {
		return data;
	}


	public String getCatalogue() {
		return Catalogue;
	}

	public void setData(List<Serializable> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RCUReceiverInfo [\n\tuuid=");
		builder.append(uuid);
		builder.append(", roomNo=");
		builder.append(roomNo);
		builder.append(", Catalogue=");
		builder.append(Catalogue);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
