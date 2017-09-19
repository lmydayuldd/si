package com.sidc.rcu.hmi.logical.mode;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.bean.GroupModeInfoBean;
import com.sidc.rcu.hmi.modesetting.request.ModeSettingListRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcugroup.RcuGroupDeviceClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RcuGroupModeProcess extends AbstractAPIProcess {
	private final ModeSettingListRequest entity;

	public RcuGroupModeProcess(final ModeSettingListRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		LogAction.getInstance().debug("entity:" + entity);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final APIContentRequest request = new APIContentRequest(entity);

		final String json = APIParser.getInstance().toJson(request);

		final String result = (String) new RcuGroupDeviceClient(blackcoreSetting.getUrl(), json).execute();

		final List<GroupModeInfoBean> grouModes = APIParser.getInstance().parse(result,
				new TypeToken<List<GroupModeInfoBean>>() {
				}.getType());

		return grouModes;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal.");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group idF).");
		}
	}
}
