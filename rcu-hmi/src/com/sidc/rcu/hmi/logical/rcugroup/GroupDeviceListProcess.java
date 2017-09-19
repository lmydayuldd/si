package com.sidc.rcu.hmi.logical.rcugroup;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.blackcore.api.ra.response.RcuGroupEnity;
import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.groupdevice.bean.DeviceInfoBean;
import com.sidc.rcu.hmi.groupdevice.bean.DeviceInfoResultBean;
import com.sidc.rcu.hmi.groupdevice.bean.GroupDeviceBean;
import com.sidc.rcu.hmi.groupdevice.bean.GroupDeviceResultBean;
import com.sidc.rcu.hmi.groupdevice.bean.RcuGroupDeviceBean;
import com.sidc.rcu.hmi.groupdevice.request.GroupDeviceRequest;
import com.sidc.rcu.hmi.groupdevice.response.GroupDeviceResponse;
import com.sidc.rcu.hmi.logical.mode.RcuGroupDeviceListProcess;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcugroup.RcuGroupDeviceClient;
import com.sidc.sdk.blackcore.rcugroup.RcuGroupListClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class GroupDeviceListProcess extends AbstractAPIProcess {
	private final GroupDeviceRequest entity;
	private BlackcoreInitialBean blackcoreSetting;

	public GroupDeviceListProcess(final GroupDeviceRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance().get(CommonDataKey.BLACKCORE_SETTING);

		final List<RcuDeviceEnity> allDeviceList = (List<RcuDeviceEnity>) new RcuGroupDeviceListProcess().execute();

		final List<GroupDeviceResultBean> GroupDeviceResults = getResult();

		final List<GroupDeviceBean> results = new ArrayList<GroupDeviceBean>();

		for (final GroupDeviceResultBean groupEntity : GroupDeviceResults) {
			List<DeviceInfoBean> devices = new ArrayList<DeviceInfoBean>();
			for (final DeviceInfoResultBean deviceEntity : groupEntity.getDevices()) {
				devices.add(new DeviceInfoBean(deviceEntity.getDeviceid(), deviceEntity.getDevice(),
						deviceEntity.getLangs().get(0).getName()));
			}
			results.add(
					new GroupDeviceBean(groupEntity.getGroupid(), groupEntity.getLangs().get(0).getName(), devices));
		}

		final List<RcuGroupEnity> rcuGroups = new RcuGroupListClient(blackcoreSetting.getUrl()).execute();
		String groupName = "";
		for (final RcuGroupEnity rcuGroupEntity : rcuGroups) {
			if (entity.getGroupid() == rcuGroupEntity.getId()) {
				groupName = rcuGroupEntity.getName();
			}
		}

		return new GroupDeviceResponse(groupName, allDeviceList, results);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<GroupDeviceResultBean> getResult() {
		List<GroupDeviceResultBean> list = new ArrayList<GroupDeviceResultBean>();

		try {
			// 發給 blackcore
			final RcuGroupDeviceBean request = new RcuGroupDeviceBean(entity.getGroupid());

			final APIContentRequest apiRequest = new APIContentRequest(request);

			final String result = toUtf8((String) new RcuGroupDeviceClient(blackcoreSetting.getUrl(),
					APIParser.getInstance().toJson(apiRequest)).execute());

			list = APIParser.getInstance().parse(result, new TypeToken<List<GroupDeviceResultBean>>() {
			}.getType());

		} catch (SiDCException e) {
			LogAction.getInstance().error(e.getMessage(), e);
		} catch (Exception e) {
			LogAction.getInstance().error(e.getMessage(), e);
		}

		return list;
	}

	private String toUtf8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "UTF-8");
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getGroupid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id)");
		}
	}
}
