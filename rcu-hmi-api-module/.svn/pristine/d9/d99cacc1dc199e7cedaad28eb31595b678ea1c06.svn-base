package com.sidc.rcu.hmi.receiver.bean;

import java.io.Serializable;
import java.util.List;

public class UdpRreceiveBean implements Serializable {
	private static final long serialVersionUID = -3747920796927798363L;

	private String uuid;
	private String roomNo;
	private String Catalogue;
	private List<RcuServiceBean> data;

	public String getUuid() {
		return uuid;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public String getCatalogue() {
		return Catalogue;
	}

	public List<RcuServiceBean> getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UdpRreceiveBean [uuid=");
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

	public UdpRreceiveBean(String uuid, String roomNo, String catalogue, List<RcuServiceBean> data) {
		super();
		this.uuid = uuid;
		this.roomNo = roomNo;
		Catalogue = catalogue;
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Catalogue == null) ? 0 : Catalogue.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((roomNo == null) ? 0 : roomNo.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}
}
