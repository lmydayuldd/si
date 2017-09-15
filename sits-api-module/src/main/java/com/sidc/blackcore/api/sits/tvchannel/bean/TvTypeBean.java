package com.sidc.blackcore.api.sits.tvchannel.bean;

import java.io.Serializable;

public class TvTypeBean implements Serializable {
	private static final long serialVersionUID = 7004413981904489326L;

	private String typeid;
	private String typename;
	private int sequence;

	public String getTypeid() {
		return typeid;
	}

	public String getTypename() {
		return typename;
	}

	public int getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TvTypeBean [typeid=");
		builder.append(typeid);
		builder.append(", typename=");
		builder.append(typename);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append("]");
		return builder.toString();
	}

	public TvTypeBean(String typeid, String typename, int sequence) {
		super();
		this.typeid = typeid;
		this.typename = typename;
		this.sequence = sequence;
	}
	
	public TvTypeBean(String typeid, String typename) {
		super();
		this.typeid = typeid;
		this.typename = typename;
	}

}
