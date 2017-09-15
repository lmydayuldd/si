package com.sidc.rcu.hmi.logical.modesetting;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modulesetting.bean.RcuDeviceBean;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcumodesetting.RcuDeviceListByGroupClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuGroupDeviceListProcess extends AbstractAPIProcess {
	private List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();

	@SuppressWarnings("unchecked")
	@Override
	protected void init() throws SiDCException, Exception {
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(null);

		final String json = APIParser.getInstance().toJson(request);

		final String response = new RcuDeviceListByGroupClient(blackcoreSetting.getUrl(), json).execute();

		// List<RcuDeviceEnity> enity = super.getGson().fromJson(json, new
		// // TypeToken<List<RcuDeviceEnity>>() {
		// // }.getType());

		list = APIParser.getInstance().parse(response, new TypeToken<List<RcuDeviceBean>>() {
		}.getType());

		LogAction.getInstance().debug("list:" + list);
	}

	/*
	 * @Override protected void init() throws SiDCException, Exception {
	 * List<RcuGroupNameEnity> groupList = new ArrayList<RcuGroupNameEnity>();
	 * groupList.add(new RcuGroupNameEnity("BULB")); groupList.add(new
	 * RcuGroupNameEnity("AIR-CONDITION"));
	 * 
	 * List<RcuDevicesNameEnity> devicesList = new
	 * ArrayList<RcuDevicesNameEnity>(); devicesList.add(new
	 * RcuDevicesNameEnity("BLIND"));
	 * 
	 * final RcuDeviceListRequest entity = new RcuDeviceListRequest();
	 * entity.setGroupList(groupList); entity.setDeviceList(devicesList);
	 * 
	 * LogAction.getInstance().debug("RcuGroupDeviceRequest:" + entity);
	 * 
	 * this.list = new
	 * RcuDeviceListByGroupClient("http://10.60.1.39:8080/blackcore",
	 * entity).execute();
	 * 
	 * LogAction.getInstance().debug("list:" + list); }
	 */

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "entity is empty.");
		}
	}
}
