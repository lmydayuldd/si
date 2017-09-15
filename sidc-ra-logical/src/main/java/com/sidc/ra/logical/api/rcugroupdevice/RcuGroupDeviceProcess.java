package com.sidc.ra.logical.api.rcugroupdevice;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuDeviceListRequest;
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuDevicesNameEnity;
import com.sidc.blackcore.api.ra.rcugroupdevice.request.RcuGroupNameEnity;
import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuDeviceGroupManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class RcuGroupDeviceProcess extends AbstractAPIProcess {
	private RcuDeviceListRequest entity;

	public RcuGroupDeviceProcess(RcuDeviceListRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();

		if (entity == null) {
			LogAction.getInstance().debug("request is null.");
			list = noneReqestProcess();
		} else {
			LogAction.getInstance().debug("request is not null.");
			list = haveRequestPorcess();
		}

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		// if (entity == null) {
		// throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is
		// illegal");
		// }
	}

	private List<RcuDeviceEnity> haveRequestPorcess() throws SiDCException, Exception {
		LogAction.getInstance().debug("haveRequestPorcess|start.");
		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();

		if (entity.getGroupList() != null) {
			for (RcuGroupNameEnity rcuGroupEntity : entity.getGroupList()) {
				list.addAll(RcuDeviceGroupManager.getInstance().searchDeviceByGroup(rcuGroupEntity.getGroupName()));
			}
		}
		LogAction.getInstance().debug("step:1/2 add list success(RcuDeviceGroupManager|searchDeviceByGroup).");

		if (entity.getDeviceList() != null) {
			for (RcuDevicesNameEnity rcuDeviceEntity : entity.getDeviceList()) {

				// 搜尋資料 By device name
				List<RcuDeviceEnity> deviceList = RcuDeviceGroupManager.getInstance()
						.searchDeviceByDevices(rcuDeviceEntity.getDevicesName());

				// 檢查 是否已存在
				for (RcuDeviceEnity entity : deviceList) {
					if (!list.contains(entity)) {
						list.add(entity);
					}
				}
			}
		}
		LogAction.getInstance().debug("step:2/2 add list success(RcuDeviceGroupManager|searchDeviceByDevices).");
		LogAction.getInstance().debug("haveRequestPorcess|end.");
		return list;
	}

	private List<RcuDeviceEnity> noneReqestProcess() throws SiDCException, Exception {

		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();

		list = RcuDeviceGroupManager.getInstance().selectAllDevice();
		LogAction.getInstance().debug("noReqestProcess|RcuDeviceGroupManager|selectAllDevice add list success.");
		return list;
	}
}
