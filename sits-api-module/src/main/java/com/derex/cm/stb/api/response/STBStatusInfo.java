package com.derex.cm.stb.api.response;

import java.util.List;

public class STBStatusInfo implements java.io.Serializable {

	private static final long serialVersionUID = -861017157309336843L;
	private List<StbStatusResponse> list;
	private int size;

	public STBStatusInfo(List<StbStatusResponse> list, int size) {
		super();
		this.list = list;
		this.size = size;
	}

	public List<StbStatusResponse> getList() {
		return list;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "STBStatusInfo [list=" + list + ", size=" + size + "]";
	}

}
