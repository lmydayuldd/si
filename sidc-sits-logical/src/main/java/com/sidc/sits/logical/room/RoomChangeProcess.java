package com.sidc.sits.logical.room;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.blackcore.api.agent.request.CheckOutRequest;
import com.sidc.blackcore.api.agent.request.GuestRequest;
import com.sidc.blackcore.api.agent.request.RoomChangeRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.common.key.RCUMode;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.dao.sits.manager.BillManager;
import com.sidc.dao.sits.manager.CheckInManager;
import com.sidc.dao.sits.manager.GuestManager;
import com.sidc.dao.sits.manager.RoomChangeManager;
import com.sidc.dao.sits.manager.StbListManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.parameter.SiTSPropertiesInfo;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;
import com.sidcsits.logical.rcu.mode.RoomModeProcess;

public class RoomChangeProcess extends AbstractAPIProcess {

	private final RoomChangeRequest enity;
	private final static int STEP = 4;
	private final int max = 9;
	private final int min = 0;

	public RoomChangeProcess(final RoomChangeRequest enity) {
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

		LogAction.getInstance().setUserId(this.enity.getOldRoomNumber());

		CheckInRequest checkInRequest = null;

		// 可以自己帶入 checkin 資料 2017/01/04
		if (enity.getCheckInEntity() == null) {
			// 抓舊的房間相關資料 要塞到 新的房號裡面
			checkInRequest = RoomChangeManager.getInstance().getCheckInRequest(enity.getOldRoomNumber(),
					enity.getNewRoomNumber());
		} else {
			if (StringUtils.isNotBlank(enity.getCheckInEntity().getCheckindate())) {
				checkInRequest = enity.getCheckInEntity();
			} else {
				//for 金旭未帶guest相關資訊
				List<GuestRequest> guestlist = GuestManager.getInstance().findGuestInfo(enity.getOldRoomNumber());
				GuestRequest guest = null;
				for (GuestRequest guestRequest : guestlist) {
					guest = new GuestRequest(guestRequest.getGuestno(), enity.getCheckInEntity().getGuests().get(0).getFirstname(), guestRequest.getLastname(),
							guestRequest.getBirthd(), guestRequest.getDepdate(), guestRequest.getGender());
				}
				String chki_time = BillManager.getInstance().findBillCheckinDate(enity.getCheckInEntity().getBillno(),
						enity.getOldRoomNumber());
				guestlist.clear();
				guestlist.add(guest);
				checkInRequest = new CheckInRequest(enity.getNewRoomNumber(), chki_time, guestlist, "",
						enity.getCheckInEntity().getBillno(), enity.getCheckInEntity().getTvright());
			}
			// new CheckInProcess(checkInRequest).check();
		}
		LogAction.getInstance().debug("Step 1/" + STEP + " check in request=" + enity + ".");

		/**
		 * 還沒成案之前 先關閉 2017/06/29 // 2017/05/25 新增 pin code需求 String pincode =
		 * getRandomString();
		 * 
		 * int index = 0; while
		 * (PinCodeManager.getInstance().checkPinCodeExist(pincode)) { pincode =
		 * getRandomString(); if (index++ > 10) { throw new
		 * SiDCException(APIStatus.DATA_DOES_EXIST, "repeat of pin code."); } }
		 * LogAction.getInstance().debug("Step 2/" + STEP +
		 * " get pin code success.");
		 **/
		CheckOutRequest checkOutRequet = new CheckOutRequest(enity.getOldRoomNumber());
		LogAction.getInstance().debug("Step 3/" + STEP + " get CheckOutRequest success." + checkOutRequet);

		RoomChangeManager.getInstance().roomChange(enity, checkInRequest, checkOutRequet, null);
		LogAction.getInstance().debug("Step 4/" + STEP + " roomChange success.");

		// check in close radio and set welcome page.
		List<String> list = StbListManager.getInstance().listStbIp(enity.getNewRoomNumber());
		String radioURL = SystemPropertiesManager.getInstance().findPropertiesMessage(SiTSPropertiesInfo.RADIOURL);
		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
		try {
			// relay
			HttpClientUtils.sendGetTORelay(radioURL + PageList.RADIO_CLOSE, list);
			LogAction.getInstance().debug("relay success");
		} catch (Exception e) {
			LogAction.getInstance().debug("relay error:" + e);
		}
		try {
			// STB Redirect Page
			HttpClientUtils.sendPostWithRedirectPageSTB(sitsUrl + PageList.STB_WELCOME_PAGE, list,
					PageList.WELCOME_PAGE);
			LogAction.getInstance().debug("RedirectPage success");
		} catch (Exception e) {
			LogAction.getInstance().debug("RedirectPage STB error:" + e);
		}
		try {
			// RCU Command
			new RoomModeProcess(new RcuRoomMode(enity.getNewRoomNumber(), RCUMode.CHECKIN)).execute();
			LogAction.getInstance().debug("RCU Check In Command success");
		} catch (Exception e) {
			LogAction.getInstance().debug("RCU Check In Command error:" + e);
		}

		// check out set stb and close radio.
		list = StbListManager.getInstance().listStbIp(enity.getOldRoomNumber());

		try {
			// relay
			HttpClientUtils.sendGetTORelay(radioURL + PageList.RADIO_CLOSE, list);
			LogAction.getInstance().debug("RADIO CLOSE success");
		} catch (Exception e) {
			LogAction.getInstance().debug("relay error : " + e);
		}

		try {
			// STB Check Out
			HttpClientUtils.sendPostWithCheckOutSTB(sitsUrl + PageList.STB_CHECK_OUT, list);
			LogAction.getInstance().debug("STB Check Out success");
		} catch (Exception e) {
			LogAction.getInstance().debug("STB CheckOut error : " + e);
		}

		try {
			// STB reboot
			HttpClientUtils.sendPostWithRebootSTB(sitsUrl + PageList.STB_REBOOT, list);
			LogAction.getInstance().debug("STB reboot success");
		} catch (Exception e) {
			LogAction.getInstance().debug("STB reboot error : " + e);
		}

		try {
			// RCU Command
			new RoomModeProcess(new RcuRoomMode(enity.getOldRoomNumber(), RCUMode.CHECKOUT)).execute();
			LogAction.getInstance().debug("RCU Check Out command success");
		} catch (Exception e) {
			LogAction.getInstance().debug("RCU Check Out command : " + e);
		}

		LogAction.getInstance().debug("step STB reboot and check out success");
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}

		if (StringUtils.isBlank(enity.getOldRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of old room No.");
		}

		if (!CheckInManager.getInstance().findRoom(enity.getOldRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find new room no.");
		}

		if (StringUtils.isBlank(enity.getNewRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of new room No.");
		}

		if (!CheckInManager.getInstance().findRoom(enity.getNewRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find new room no.");
		}

		if (StringUtils.isBlank(CheckInManager.getInstance().findRoomCheckOutStatus(enity.getNewRoomNumber()))) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "new room no is not check out.");
		}

	}

	private String getRandomString() {
		String pinCode = "";

		Random rand = new Random();

		for (int i = 0; i < 6; i++) {
			pinCode += String.valueOf(rand.nextInt((max - min) + 1) + min);
		}

		return pinCode;
	}
}
