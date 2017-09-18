package com.sidc.rcu.hmi.api.request;


public class APIRequest implements java.io.Serializable {

	private static final long serialVersionUID = 6269915269225513831L;
	private Object data;
	private int page = 1;
	private int limit = 10;

	public APIRequest(Object data) {
		super();
		this.data = data;
	}

	public APIRequest(Object data, int page, int limit) {
		super();
		this.data = data;
		this.page = page;
		this.limit = limit;
	}

	public Object getData() {
		return data;
	}

	public int getPage() {
		return page;
	}

	public int getLimit() {
		return limit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIRequest [data=\n");
		builder.append(data);
		builder.append(", page=\n");
		builder.append(page);
		builder.append(", limit=\n");
		builder.append(limit);
		builder.append("]");
		return builder.toString();
	}
}
