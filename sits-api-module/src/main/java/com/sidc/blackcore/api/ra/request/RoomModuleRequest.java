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
	private List<RoomModuleBean> modules;

	public RoomModuleRequest(List<RoomModuleBean> modules) {
		super();
		this.modules = modules;
	}

	public List<RoomModuleBean> getModules() {
		return modules;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomModuleRequest [\n\tmodules=");
		builder.append(modules);
		builder.append("]");
		return builder.toString();
	}

}
