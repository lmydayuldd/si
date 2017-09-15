package com.sidc.blackcore.api.sits.tvchannel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TvListBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7969990656813218743L;
	private String catalogue;
	private String catalogueid;
	private int sequence;
	private List<TvChannelBean> channellist = new ArrayList<TvChannelBean>();

	public String getCatalogue() {
		return catalogue;
	}

	public String getCatalogueid() {
		return catalogueid;
	}

	public int getSequence() {
		return sequence;
	}

	public List<TvChannelBean> getChannellist() {
		return channellist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TvListBean [catalogue=");
		builder.append(catalogue);
		builder.append(", catalogueid=");
		builder.append(catalogueid);
		builder.append(", sequence=");
		builder.append(sequence);
		builder.append(", channellist=");
		builder.append(channellist);
		builder.append("]");
		return builder.toString();
	}

	public TvListBean(String catalogue, String catalogueid, int sequence, List<TvChannelBean> channellist) {
		super();
		this.catalogue = catalogue;
		this.catalogueid = catalogueid;
		this.sequence = sequence;
		this.channellist = channellist;
	}

}
