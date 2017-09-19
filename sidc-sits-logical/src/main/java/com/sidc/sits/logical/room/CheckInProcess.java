package com.sidc.sits.logical.room;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.blackcore.api.agent.request.GuestRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.common.key.CommonCatalogueRCUKey;
import com.sidc.configuration.common.key.PMSKey;
import com.sidc.configuration.common.key.RCUMode;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.dao.sits.manager.CheckInManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.dao.sits.manager.StbListManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.rcu.connector.bean.receiver.PMSReceiver;
import com.sidc.rcu.connector.bean.receiver.RCUReceiverInfo;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.parameter.SiTSPropertiesInfo;
import com.sidc.sits.logical.rcu.mode.RoomModeProcess;
import com.sidc.sits.logical.utils.DateTimeUtils;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.net.UDPClientBroadcast;
import com.sidc.utils.net.UDPConnection;
import com.sidc.utils.status.APIStatus;

public class CheckInProcess extends AbstractAPIProcess {
	private final CheckInRequest enity;

	private final int STEP = 5;
	private final int max = 9;
	private final int min = 0;

	public CheckInProcess(final CheckInRequest enity) {
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

		/**
		 * 還沒成案之前 先關閉 2017/06/29 // 2017/05/25 新增 pin code需求 String pincode =
		 * getRandomString();
		 * 
		 * int index = 0; while
		 * (PinCodeManager.getInstance().checkPinCodeExist(pincode)) { pincode =
		 * getRandomString(); if (index++ > 10) { throw new
		 * SiDCException(APIStatus.DATA_DOES_EXIST, "repeat of pin code."); } }
		 * LogAction.getInstance().debug("Step 1/" + STEP +
		 * " get pincode success.");
		 **/
		CheckInManager.getInstance().checkIn(enity, null);

		LogAction.getInstance().debug("Step 2/" + STEP + " do check in success.");

		final List<String> list = StbListManager.getInstance().listStbIp(enity.getRoomno());

		final String radioURL = SystemPropertiesManager.getInstance()
				.findPropertiesMessage(SiTSPropertiesInfo.RADIOURL);
		try {
			// relay
			HttpClientUtils.sendGetTORelay(radioURL + PageList.RADIO_CLOSE, list);
		} catch (Exception e) {
			LogAction.getInstance().debug("relay:" + e);
		}

		try {
			final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
			// STB welcome
			HttpClientUtils.sendPostWithRedirectPageSTB(sitsUrl + PageList.STB_WELCOME_PAGE, list,
					PageList.WELCOME_PAGE);
		} catch (Exception e) {
			LogAction.getInstance().debug("STB welcome:" + e);
		}

		LogAction.getInstance().debug("Step 3/" + STEP + " set welcome message success.");

		try {
			// RCU Command
			new RoomModeProcess(new RcuRoomMode(enity.getRoomno(), RCUMode.CHECKIN)).execute();
		} catch (Exception e) {
			LogAction.getInstance().debug("RCU CheckIn:" + e);
		}

		LogAction.getInstance().debug("Step 4/" + STEP + " set RCU checkin command success.");

		List<Serializable> objs = new ArrayList<Serializable>();
		objs.add(new PMSReceiver(PMSKey.CHECKIN));

		final RCUReceiverInfo receiver = new RCUReceiverInfo(LogAction.getInstance().getUUID(), this.enity.getRoomno(),
				CommonCatalogueRCUKey.PMS, objs);

		broadcastPMS(8026, receiver);

		LogAction.getInstance().debug("Step 5/" + STEP + " broadcast Checkin success.");

		return null;
	}

	private void broadcastPMS(final int target, final RCUReceiverInfo receiver) throws SiDCException {
		UDPClientBroadcast broadcast = null;
		try {
			broadcast = new UDPClientBroadcast(new UDPConnection());
			broadcast.send(new Gson().toJson(receiver).getBytes(), target);
		} finally {
			broadcast.close();
		}
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no request.");
		}
		if (!CheckInManager.getInstance().findRoom(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find room no.");
		}
		if (StringUtils.isBlank(enity.getCheckindate())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(check in date).");
		}

		final DateTimeUtils dateUtils = new DateTimeUtils(new SimpleDateFormat("yyyy/MM/dd"));
		if (!dateUtils.isDate(enity.getCheckindate())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
					"illegal of request(check in date format error(yyyy/MM/dd)).");
		}

		List<GuestRequest> guestList = new ArrayList<GuestRequest>();

		// 2017/06/28 新增 預計 check out 需求
		for (GuestRequest guestEnity : enity.getGuests()) {
			/**
			 * 還沒成案之前 先關閉 2017/06/29 if
			 * (StringUtils.isBlank(guestEnity.getDepdate())) { throw new
			 * SiDCException(APIStatus.ILLEGAL_ARGUMENT,
			 * "illegal of request(depdate)."); } if
			 * (!isDate(guestEnity.getDepdate())) { throw new
			 * SiDCException(APIStatus.ILLEGAL_ARGUMENT,
			 * "illegal of request(depdate format error(yyyy/MM/dd))."); } if
			 * (!checkSequence(enity.getCheckindate(), guestEnity.getDepdate()))
			 * { throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
			 * "illegal of request(check in time,depdate sequence error)."); }
			 **/

			if (!dateUtils.isDate(guestEnity.getDepdate())) {
				guestEnity.setDepdate(null);
			}
			if (!StringUtils.isBlank(guestEnity.getBirthd())) {
				// 檢查是不是時間格式，因不是必填資訊，防呆，所以給空
				String birday = null;
				if (dateUtils.isDate(guestEnity.getBirthd())) {
					birday = guestEnity.getBirthd();
				}
				guestEnity = new GuestRequest(guestEnity.getGuestno(), guestEnity.getFirstname(),
						guestEnity.getLastname(), birday, guestEnity.getDepdate(), guestEnity.getSalutation());
			}

			if (StringUtils.isBlank(guestEnity.getFirstname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "First name is null.");
			}

			guestList.add(guestEnity);
		}
		enity.setGuests(guestList);

		if (RoomManager.getInstance().isCheckin(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room no is not check out.");
		}
		if (!StringUtils.isBlank(enity.getBillno())) {
			if (CheckInManager.getInstance().checkBillNoRoomno(enity.getBillno(), enity.getRoomno())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "same bill no.");
			}
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
