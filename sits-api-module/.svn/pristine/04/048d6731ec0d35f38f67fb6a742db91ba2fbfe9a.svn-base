package com.sidc.blackcore.api.sits.tvchannel.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvListBean;

public class TvChannelListResponse implements Serializable {

	private static final long serialVersionUID = -7406590233216099337L;

	private List<TvListBean> tvlist = new ArrayList<TvListBean>();

	public List<TvListBean> getTvlist() {
		return tvlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TvChannelListResponse [tvlist=");
		builder.append(tvlist);
		builder.append("]");
		return builder.toString();
	}

	public TvChannelListResponse(List<TvListBean> tvlist) {
		super();
		this.tvlist = tvlist;
	}

}
