package com.sidc.blackcore.api.sits.shop.bean;

import java.io.Serializable;
import java.util.Arrays;

public class ShopPhotoUploadBean implements Serializable {
	private static final long serialVersionUID = 6529885314996084558L;
	private byte[] photo;
	private String name;
	private String extension;

	public byte[] getPhoto() {
		return photo;
	}

	public String getName() {
		return name;
	}

	public String getExtension() {
		return extension;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopPhotoUploadBean [photo=");
		builder.append(Arrays.toString(photo));
		builder.append(", name=");
		builder.append(name);
		builder.append(", extension=");
		builder.append(extension);
		builder.append("]");
		return builder.toString();
	}

	public ShopPhotoUploadBean(byte[] photo, String name, String extension) {
		super();
		this.photo = photo;
		this.name = name;
		this.extension = extension;
	}

}
