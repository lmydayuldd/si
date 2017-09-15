package com.derex.cm.sits.api.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.derex.cm.sits.api.bean.PhotoUploadBean;

public class PhotoUploadRequest implements Serializable {
	private static final long serialVersionUID = 7636378895277270907L;

	private List<String> foldername = new ArrayList<String>();
	private String type;
	private List<PhotoUploadBean> list = new ArrayList<PhotoUploadBean>();

	public List<String> getFoldername() {
		return foldername;
	}

	public String getType() {
		return type;
	}

	public List<PhotoUploadBean> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoUploadRequest [foldername=");
		builder.append(foldername);
		builder.append(", type=");
		builder.append(type);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public PhotoUploadRequest(List<String> foldername, String type, List<PhotoUploadBean> list) {
		super();
		this.foldername = foldername;
		this.type = type;
		this.list = list;
	}

}
