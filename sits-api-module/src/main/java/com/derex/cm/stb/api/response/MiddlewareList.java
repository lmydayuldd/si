package com.derex.cm.stb.api.response;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Allen Huang
 *
 */
public class MiddlewareList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8447869609801680670L;
	private List<MiddlewareVersionResponse> list;
	
	public MiddlewareList (List<MiddlewareVersionResponse> list) {
		super();
		this.list = list;
	}

	public List<MiddlewareVersionResponse> getList() {
		return list;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MiddlewareList [list=\n");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
}
