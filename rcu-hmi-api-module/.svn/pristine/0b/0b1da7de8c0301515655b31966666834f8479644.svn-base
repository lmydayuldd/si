package com.sidc.rcu.hmi.modeledevicesetting.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.rcu.hmi.modulesetting.bean.ModuleDeviceListBean;
import com.sidc.rcu.hmi.modulesetting.bean.RcuDeviceBean;

public class ModuleDeviceSettingListResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311138910106142821L;
	private List<RcuDeviceBean> allDeviceList = new ArrayList<RcuDeviceBean>();
	private List<ModuleDeviceListBean> moduleDeviceList = new ArrayList<ModuleDeviceListBean>();

	public List<RcuDeviceBean> getAllDeviceList() {
		return allDeviceList;
	}

	public List<ModuleDeviceListBean> getModuleDeviceList() {
		return moduleDeviceList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModuleDeviceSettingListResponse [moduleDeviceList=");
		builder.append(moduleDeviceList);
		builder.append("]");
		return builder.toString();
	}

	public ModuleDeviceSettingListResponse(List<RcuDeviceBean> allDeviceList,
			List<ModuleDeviceListBean> moduleDeviceList) {
		super();
		this.allDeviceList = allDeviceList;
		this.moduleDeviceList = moduleDeviceList;
	}

}
