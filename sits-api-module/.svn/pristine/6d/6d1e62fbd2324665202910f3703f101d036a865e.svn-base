package com.derex.cm.sits.api.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhotoDeleteRequest implements Serializable {
	private static final long serialVersionUID = 7740923086309023988L;
	private String type;
	private List<String> deletefoldername = new ArrayList<String>();
	private List<String> photoname = new ArrayList<String>();

	public String getType() {
		return type;
	}

	public List<String> getDeletefoldername() {
		return deletefoldername;
	}

	public List<String> getPhotoname() {
		return photoname;
	}

	public PhotoDeleteRequest(String type, List<String> deletefoldername, List<String> photoname) {
		super();
		this.type = type;
		this.deletefoldername = deletefoldername;
		this.photoname = photoname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoDeleteRequest [type=");
		builder.append(type);
		builder.append(", deletefoldername=");
		builder.append(deletefoldername);
		builder.append(", photoname=");
		builder.append(photoname);
		builder.append("]");
		return builder.toString();
	}

}
