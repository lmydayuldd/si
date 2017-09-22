package com.sidc.rcu.hmi.logical.module;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.logical.mode.RcuGroupDeviceListProcess;
import com.sidc.rcu.hmi.modeledevicesetting.response.ModuleDeviceSettingListResponse;
import com.sidc.rcu.hmi.moduledevicesetting.request.ModuleDeviceRequest;
import com.sidc.rcu.hmi.modulesetting.bean.ModuleDeviceListBean;
import com.sidc.rcu.hmi.modulesetting.bean.RcuDeviceBean;
import com.sidc.sdk.blackcore.rcumodule.RcuModuleDeviceListClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class ModuleDeviceListProcess extends AbstractAPIProcess {
	private List<RcuDeviceBean> allDeviceList = new ArrayList<RcuDeviceBean>();
	private List<ModuleDeviceListBean> moduleDeviceList = new ArrayList<ModuleDeviceListBean>();
	private ModuleDeviceRequest entity;

	public ModuleDeviceListProcess(final ModuleDeviceRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		this.allDeviceList = (List<RcuDeviceBean>) new RcuGroupDeviceListProcess().execute();

		final APIContentRequest request = new APIContentRequest(entity);

		String moduleRespone = (String) new RcuModuleDeviceListClient("http://10.60.1.39:7056/blackcore",
				APIParser.getInstance().toJson(request)).execute();

		moduleDeviceList = APIParser.getInstance().parse(moduleRespone, new TypeToken<List<ModuleDeviceListBean>>() {
		}.getType());

		return new ModuleDeviceSettingListResponse(allDeviceList, moduleDeviceList);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "entity is empty.");
		}
	}
}
