/**
 * 
 */
package com.sidc.dao.ra.response;

import java.io.Serializable;
import java.util.List;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 * @author Nandin
 *
 */
public class DeviceCatalogue implements Serializable {

	private static final long serialVersionUID = 2295218801889978611L;

	@QuerySqlField(index = true)
	private String catalogue;
	private int command;
	private List<DeviceEnity> devices;
	private List<Language> langs;

	public DeviceCatalogue(String catalogue, List<DeviceEnity> devices, List<Language> langs) {
		super();
		this.catalogue = catalogue;
		this.devices = devices;
		this.langs = langs;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getCommand() {
		return command;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public List<DeviceEnity> getDevices() {
		return devices;
	}

	public List<Language> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceCatalogue [\n\tcatalogue=");
		builder.append(catalogue);
		builder.append(", command=");
		builder.append(command);
		builder.append(", devices=");
		builder.append(devices);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

}
