package com.sidc.blackcore.api.sits.tvchannel.bean;

import java.io.Serializable;

public class TvChannelListBean implements Serializable {

	private static final long serialVersionUID = -1195073073483182252L;

	private String id;
	private String typeid;
	private String channelno;
	private int sequence;
	private String name;
	private String iplowbitrate;
	private String port;
	private String image;

	public String getId() {
		return id;
	}

	public String getTypeid() {
		return typeid;
	}

	public String getChannelno() {
		return channelno;
	}

	public int getSequence() {
		return sequence;
	}

	public String getName() {
		return name;
	}

	public String getIplowbitrate() {
		return iplowbitrate;
	}

	public String getPort() {
		return port;
	}

	public String getImage() {
		return image;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TvChannelListBean [id=");
		builder.append(id);
		builder.append(", typeid=");
		builder.append(typeid);
		builder.append(", channelno=");
		builder.append(channelno);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", name=");
		builder.append(name);
		builder.append(", iplowbitrate=");
		builder.append(iplowbitrate);
		builder.append(", port=");
		builder.append(port);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}

	public TvChannelListBean(String id, String typeid, String channelno, int sequence, String name, String iplowbitrate,
			String port) {
		super();
		this.id = id;
		this.typeid = typeid;
		this.channelno = channelno;
		this.sequence = sequence;
		this.name = name;
		this.iplowbitrate = iplowbitrate;
		this.port = port;
	}

	public TvChannelListBean(String id, String typeid, String channelno, int sequence, String name, String iplowbitrate,
			String port, String image) {
		super();
		this.id = id;
		this.typeid = typeid;
		this.channelno = channelno;
		this.sequence = sequence;
		this.name = name;
		this.iplowbitrate = iplowbitrate;
		this.port = port;
		this.image = image;
	}

}
