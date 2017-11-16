/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;
import java.util.List;

public class ModeInitialBean implements Serializable {
	private static final long serialVersionUID = 5427592319678171808L;
	private int id;
	private String keyname;
	private int timer;
	private List<ModeInitialGroupBean> groups;
	private List<ModeLangBean> langs;

	public int getId() {
		return id;
	}

	public String getKeyname() {
		return keyname;
	}

	public int getTimer() {
		return timer;
	}

	public List<ModeInitialGroupBean> getGroups() {
		return groups;
	}

	public List<ModeLangBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialBean [id=");
		builder.append(id);
		builder.append(", keyname=");
		builder.append(keyname);
		builder.append(", timer=");
		builder.append(timer);
		builder.append(", groups=");
		builder.append(groups);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public ModeInitialBean(int id, String keyname, int timer, List<ModeInitialGroupBean> groups,
			List<ModeLangBean> langs) {
		super();
		this.id = id;
		this.keyname = keyname;
		this.timer = timer;
		this.groups = groups;
		this.langs = langs;
	}

}
