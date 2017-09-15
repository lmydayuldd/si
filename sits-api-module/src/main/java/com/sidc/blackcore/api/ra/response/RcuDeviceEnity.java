package com.sidc.blackcore.api.ra.response;

import java.io.Serializable;

public class RcuDeviceEnity implements Serializable {
	private static final long serialVersionUID = 4992804663486556632L;
	private int id;
	private String device;
	private String gouprName;

	public int getId() {
		return id;
	}

	public String getDevice() {
		return device;
	}

	public String getGouprName() {
		return gouprName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuDeviceEnity [id=");
		builder.append(id);
		builder.append(", device=");
		builder.append(device);
		builder.append(", gouprName=");
		builder.append(gouprName);
		builder.append("]");
		return builder.toString();
	}

	public RcuDeviceEnity(int id, String device, String gouprName) {
		super();
		this.id = id;
		this.device = device;
		this.gouprName = gouprName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + ((gouprName == null) ? 0 : gouprName.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RcuDeviceEnity other = (RcuDeviceEnity) obj;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (gouprName == null) {
			if (other.gouprName != null)
				return false;
		} else if (!gouprName.equals(other.gouprName))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
