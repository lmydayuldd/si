package com.sidc.rcu.hmi.logical.room;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.bean.RcuRoomInfoBean;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.room.request.RcuRoomInfoRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.RoomRCUInfoClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.SystemStatus;

public class RcuRoomListProcess extends AbstractAPIProcess {

	private List<RcuRoomInfoRequest> list = new ArrayList<RcuRoomInfoRequest>();

	@SuppressWarnings("unchecked")
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final String request = APIParser.getInstance()
				.toJson(new RoomRCUInfoClient(blackcoreSetting.getUrl()).execute());

		this.list = APIParser.getInstance().parse(request, new TypeToken<List<RcuRoomInfoRequest>>() {
		}.getType());

		LogAction.getInstance().debug("list:" + list);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		List<RcuRoomInfoBean> roomList = new ArrayList<RcuRoomInfoBean>();

		for (final RcuRoomInfoRequest entity : list) {
			if (!StringUtils.isBlank(entity.getRcugroup())) {
				roomList.add(new RcuRoomInfoBean(entity.getRoomno(), entity.getFloor(), entity.isCheckin()));
			}
		}

		return APIParser.getInstance().toJson(roomList);
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
			throw new SiDCException(SystemStatus.HTTP_CONNECTION_FAIL, "request is null.");
		}
	}

}
