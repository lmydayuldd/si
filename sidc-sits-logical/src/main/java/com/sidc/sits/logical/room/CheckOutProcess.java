package com.sidc.sits.logical.room;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.CheckOutRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.CheckInManager;
import com.sidc.dao.sits.manager.CheckOutManager;
import com.sidc.dao.sits.manager.StbListManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.parameter.SiTSPropertiesInfo;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class CheckOutProcess extends AbstractAPIProcess {

	private final CheckOutRequest enity;
	private final static int STEP = 3;

	public CheckOutProcess(final CheckOutRequest enity) {
		this.enity = enity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		LogAction.getInstance().debug("Request:" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().setUserId(this.enity.getRoomno());

		CheckOutManager.getInstance().checkout(enity);
		LogAction.getInstance().debug("Step 1/" + STEP + " do check out success.");

		final List<String> list = StbListManager.getInstance().listStbIp(enity.getRoomno());
		LogAction.getInstance().debug("Step 2/" + STEP + " get stb list from room success.");

		final String radioURL = SystemPropertiesManager.getInstance()
				.findPropertiesMessage(SiTSPropertiesInfo.RADIOURL);
		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());

		LogAction.getInstance().debug("Step 3/" + STEP + " radio url:" + radioURL + " sits url:" + sitsUrl + ".");

		try {
			// relay
			HttpClientUtils.sendGetTORelay(radioURL + PageList.RADIO_CLOSE, list);
			LogAction.getInstance().debug("radio close success.");
		} catch (Exception e) {
			LogAction.getInstance().debug("Relay:" + e);
		}

		try {
			// STB reboot
			HttpClientUtils.sendPostWithCheckOutSTB(sitsUrl + PageList.STB_CHECK_OUT, list);
			LogAction.getInstance().debug("check out STB success.");
		} catch (Exception e) {
			LogAction.getInstance().debug("CheckOut STB:" + e);
		}

		try {
			// STB
			HttpClientUtils.sendPostWithRebootSTB(sitsUrl + PageList.STB_REBOOT, list);
			LogAction.getInstance().debug("step STB reboot success.");
		} catch (Exception e) {
			LogAction.getInstance().debug("Reboot STB:" + e);
		}

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
		}
		if (!CheckInManager.getInstance().findRoom(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find room no.");
		}
	}

}
