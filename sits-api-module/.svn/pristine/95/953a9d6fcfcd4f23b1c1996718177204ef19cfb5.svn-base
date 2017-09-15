package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbUpgradeFirmwareRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6026136931936725892L;
	private List<String> stbip;
	private String filename;
	public StbUpgradeFirmwareRequest(List<String> stbip, String filename) {
		this.stbip = stbip;
		this.filename = filename;
	}
	public List<String> getStbip() {
		return stbip;
	}
	public String getFilename() {
		return filename;
	}
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("StbUpgradeFirmwareRequest [stbip=\n");
		builder.append(stbip);
		builder.append(", filename=\n");
		builder.append(filename);
		builder.append("]");
		return builder.toString();
	}
}
