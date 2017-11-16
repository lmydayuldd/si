/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.bean;

import java.io.Serializable;
import java.util.List;

public class ModeInitialInsertBean implements Serializable {
	private static final long serialVersionUID = -6136831436630493407L;
	private int modeid;
	private String keyname;
	private int status;
	private int timer;
	private List<ModeInitialModuleBean> modules;
	private List<ModeLangBean> langs;

	public int getModeid() {
		return modeid;
	}

	public String getKeyname() {
		return keyname;
	}

	public int getStatus() {
		return status;
	}

	public int getTimer() {
		return timer;
	}

	public List<ModeInitialModuleBean> getModules() {
		return modules;
	}

	public List<ModeLangBean> getLangs() {
		return langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialInsertBean [modeid=");
		builder.append(modeid);
		builder.append(", keyname=");
		builder.append(keyname);
		builder.append(", status=");
		builder.append(status);
		builder.append(", timer=");
		builder.append(timer);
		builder.append(", modules=");
		builder.append(modules);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public ModeInitialInsertBean(int modeid, String keyname, int status, int timer, List<ModeInitialModuleBean> modules,
			List<ModeLangBean> langs) {
		super();
		this.modeid = modeid;
		this.keyname = keyname;
		this.status = status;
		this.timer = timer;
		this.modules = modules;
		this.langs = langs;
	}

}
