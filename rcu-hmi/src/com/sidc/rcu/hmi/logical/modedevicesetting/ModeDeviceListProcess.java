package com.sidc.rcu.hmi.logical.modedevicesetting;

import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.logical.modesetting.RcuGroupDeviceListProcess;
import com.sidc.rcu.hmi.logical.modesetting.RcuModeDeviceProcess;
import com.sidc.rcu.hmi.modedevicesetting.response.ModeDeviceSettingListResponse;
import com.sidc.rcu.hmi.modesetting.bean.RcuModeDeviceListBean;
import com.sidc.rcu.hmi.moduledevicesetting.request.RcuModeDeviceRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class ModeDeviceListProcess extends AbstractAPIProcess {
	private List<RcuDeviceEnity> allDeviceList = new ArrayList<RcuDeviceEnity>();
	private List<RcuModeDeviceListBean> modeDeviceList = new ArrayList<RcuModeDeviceListBean>();
	private RcuModeDeviceRequest entity;

	public ModeDeviceListProcess(final RcuModeDeviceRequest entity) {
		this.entity = entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		this.allDeviceList = (List<RcuDeviceEnity>) new RcuGroupDeviceListProcess().execute();
		this.modeDeviceList = (List<RcuModeDeviceListBean>) new RcuModeDeviceProcess(entity).execute();
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		return new ModeDeviceSettingListResponse(allDeviceList, modeDeviceList);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "entity is empty.");
		}
	}

}
