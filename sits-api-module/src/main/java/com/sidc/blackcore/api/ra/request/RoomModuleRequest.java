/**
 * 
 */
package com.sidc.blackcore.api.ra.request;

import java.io.Serializable;
import java.util.List;

import com.sidc.raudp.bean.RoomModuleBean;

/**
 * @author Nandin
 *
 */
public class RoomModuleRequest implements Serializable {

	private static final long serialVersionUID = 2305309666874161829L;
	private boolean isinitial;
	private List<RoomModuleBean> modules;

	public List<RoomModuleBean> getModules() {
		return modules;
	}

	public boolean isInitial() {
		return isinitial;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomModuleRequest [isinitial=");
		builder.append(isinitial);
		builder.append(", modules=");
		builder.append(modules);
		builder.append("]");
		return builder.toString();
	}

	public RoomModuleRequest(boolean isinitial, List<RoomModuleBean> modules) {
		super();
		this.isinitial = isinitial;
		this.modules = modules;
	}

}
