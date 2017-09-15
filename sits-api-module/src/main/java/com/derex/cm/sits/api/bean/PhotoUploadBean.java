package com.derex.cm.sits.api.bean;

import java.io.Serializable;
import java.util.Arrays;

public class PhotoUploadBean implements Serializable {
	private static final long serialVersionUID = 1825673580473764846L;
	private byte[] photo;
	private String url;

	public byte[] getPhoto() {
		return photo;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoUploadBean [photo=");
		builder.append(Arrays.toString(photo));
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

	public PhotoUploadBean(byte[] photo, String url) {
		super();
		this.photo = photo;
		this.url = url;
	}

}
