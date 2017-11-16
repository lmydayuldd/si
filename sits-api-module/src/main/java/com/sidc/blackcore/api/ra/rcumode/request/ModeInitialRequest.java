/**
 * 
 */
package com.sidc.blackcore.api.ra.rcumode.request;

import java.io.Serializable;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialBean;

public class ModeInitialRequest implements Serializable {
	private static final long serialVersionUID = 2692641788891843452L;
	private List<ModeInitialBean> modes;

	public List<ModeInitialBean> getModes() {
		return modes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModeInitialRequest [modes=");
		builder.append(modes);
		builder.append("]");
		return builder.toString();
	}

	public ModeInitialRequest(List<ModeInitialBean> modes) {
		super();
		this.modes = modes;
	}

}
