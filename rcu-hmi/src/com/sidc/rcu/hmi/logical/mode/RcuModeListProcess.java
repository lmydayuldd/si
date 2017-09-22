package com.sidc.rcu.hmi.logical.mode;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.modesetting.response.ModeListResponse;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcu.mode.RcuModeListClient;
import com.sidc.utils.exception.SiDCException;

public class RcuModeListProcess extends AbstractAPIProcess {

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final String result = new RcuModeListClient(blackcoreSetting.getUrl()).execute();

		List<ModeListResponse> list = APIParser.getInstance().parse(result, new TypeToken<List<ModeListResponse>>() {
		}.getType());

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}
}
