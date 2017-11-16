package com.derex.cm.stb.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbTB009ScanList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2811890820733300277L;
	private List<StbTB009ScaneResponse> list;
	
	public StbTB009ScanList(List<StbTB009ScaneResponse> list) {
		super();
		this.list = list;
	}

	public List<StbTB009ScaneResponse> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbTB009ScanList [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
	
}
