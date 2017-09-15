package com.sidc.rcu.hmi.logical.rcugroup;

import com.sidc.rcu.hmi.api.parser.APIParser;
import com.sidc.rcu.hmi.api.request.APIContentRequest;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.group.bean.RcuGroupDeleteBean;
import com.sidc.rcu.hmi.group.request.GroupDeleteRequest;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.sdk.blackcore.rcugroup.RcuGroupDeleteClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class GroupDeleteProcess extends AbstractAPIProcess {
	private final GroupDeleteRequest entity;

	public GroupDeleteProcess(final GroupDeleteRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		// 發給 blackcore
		final RcuGroupDeleteBean request = new RcuGroupDeleteBean(entity.getGroupid());

		final APIContentRequest apiRequest = new APIContentRequest(request);

		final BlackcoreInitialBean blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance()
				.get(CommonDataKey.BLACKCORE_SETTING);

		final String result = (String) new RcuGroupDeleteClient(blackcoreSetting.getUrl(),
				APIParser.getInstance().toJson(apiRequest)).execute();

		return result;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}
		if (entity.getGroupid() <= 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(group id)");
		}
	}
}
