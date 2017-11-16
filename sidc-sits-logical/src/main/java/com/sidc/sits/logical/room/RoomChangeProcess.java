package com.sidc.sits.logical.room;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.blackcore.api.agent.request.CheckOutRequest;
import com.sidc.blackcore.api.agent.request.GuestRequest;
import com.sidc.blackcore.api.agent.request.RoomChangeRequest;
import com.sidc.blackcore.bean.configuration.BlackcoreConfiguration;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.Configuration;
import com.sidc.configuration.SETTING;
import com.sidc.configuration.blackcore.RCUServiceConfiguration;
import com.sidc.configuration.common.key.CommonCatalogueRCUKey;
import com.sidc.configuration.common.key.PMSKey;
import com.sidc.configuration.common.key.RCUMode;
import com.sidc.configuration.conf.Env;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.dao.sits.manager.BillManager;
import com.sidc.dao.sits.manager.CheckInManager;
import com.sidc.dao.sits.manager.GuestManager;
import com.sidc.dao.sits.manager.RoomChangeManager;
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
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.net.UDPClientBroadcast;
import com.sidc.utils.net.UDPConnection;
import com.sidc.utils.status.APIStatus;

public class RoomChangeProcess extends AbstractAPIProcess {

	private final RoomChangeRequest enity;
	private final static int STEP = 5;

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

		if (!StringUtils.isBlank(enity.getNewRoomNumber())) {
			roomChange(checkInRequest);
		} else {
			checkInRequest = enity.getCheckInEntity();
			LogAction.getInstance().debug("Step 1/" + STEP + " check in request=" + enity + ".");

			boolean isExist = RoomManager.getInstance().isExist(enity.getOldRoomNumber(), checkInRequest.getBillno());
			LogAction.getInstance()
					.debug("Step 2/" + STEP + " whether the room no. and the bill no. exist. " + isExist);
			if (!isExist) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "bill no. does not exist.");
			}
			if (!StringUtils.isBlank(checkInRequest.getTvright())) {
				RoomManager.getInstance().updateTvright(enity.getOldRoomNumber(), checkInRequest.getTvright());
				LogAction.getInstance().debug("Step 3/" + STEP + " change TVRight success.");
			}
			List<GuestRequest> guests = checkInRequest.getGuests();
			GuestManager.getInstance().updateGuest(enity.getOldRoomNumber(), guests);
			LogAction.getInstance().debug("Step 4/" + STEP + " change guests info success.");
		}

		RCUServiceConfiguration rcuConfig = null;
		try {
			BlackcoreConfiguration configure = (BlackcoreConfiguration) DataCenter.getInstance()
					.get(SETTING.CONFIGURATION);
			rcuConfig = Configuration.readRCUServiceConfiguration(new File(Env.SYSTEM_DEF_PATH + configure.getRcu()));
			LogAction.getInstance().debug("Step 5/" + STEP + " get RCU configration URL success.");
		} catch (Exception e) {
			LogAction.getInstance().debug("RCU path:" + e);
		}

		if (rcuConfig.isEnable()) {
			List<Serializable> objs = new ArrayList<Serializable>();
			objs.add(new PMSReceiver(PMSKey.CHECKOUT));

			broadcastPMS(8026, new RCUReceiverInfo(LogAction.getInstance().getUUID(), this.enity.getOldRoomNumber(),
					CommonCatalogueRCUKey.PMS, objs));

			objs = new ArrayList<Serializable>();
			objs.add(new PMSReceiver(PMSKey.CHECKIN));

			broadcastPMS(8026, new RCUReceiverInfo(LogAction.getInstance().getUUID(), this.enity.getNewRoomNumber(),
					CommonCatalogueRCUKey.PMS, objs));
			LogAction.getInstance().debug("broadcast check out success.");
		}

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
		if (StringUtils.isBlank(enity.getOldRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of old room No.");
		}
		if (!CheckInManager.getInstance().findRoom(enity.getOldRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find old room no.");
		}
		if (StringUtils.isBlank(enity.getNewRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of new room No.");
		}
		if (!CheckInManager.getInstance().findRoom(enity.getNewRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find new room no.");
		}
		if (RoomManager.getInstance().isCheckin(enity.getNewRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room no is not check out.");
		}
		final DateTimeUtils dateUtils = new DateTimeUtils(new SimpleDateFormat("yyyy/MM/dd"));

		List<GuestRequest> guestList = new ArrayList<GuestRequest>();
		for (GuestRequest guestEnity : enity.getCheckInEntity().getGuests()) {
			if (!StringUtils.isBlank(guestEnity.getBirthd())) {
				// 檢查是不是時間格式，因不是必填資訊，防呆，所以給空
				String birday = null;
				if (dateUtils.isDate(guestEnity.getBirthd())) {
					birday = guestEnity.getBirthd();
				}
				guestEnity = new GuestRequest(guestEnity.getGuestno(), guestEnity.getFirstname(),
						guestEnity.getLastname(), birday, guestEnity.getDepdate(), guestEnity.getGroupid(),
						guestEnity.getSalutation());
			}
			guestList.add(guestEnity);
		}
		enity.getCheckInEntity().setGuests(guestList);
	}

	private Object roomChange(CheckInRequest checkInRequest) throws SiDCException, Exception {

		if (!CheckInManager.getInstance().findRoom(enity.getNewRoomNumber())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find new room no.");
		}

		if (StringUtils.isBlank(CheckInManager.getInstance().findRoomCheckOutStatus(enity.getNewRoomNumber()))) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "new room no is not check out.");
		}

		// 可以自己帶入 checkin 資料 2017/01/04
		if (enity.getCheckInEntity() == null) {
			// 抓舊的房間相關資料 要塞到 新的房號裡面
			checkInRequest = RoomChangeManager.getInstance().getCheckInRequest(enity.getOldRoomNumber(),
					enity.getNewRoomNumber());
		} else {
			if (StringUtils.isNotBlank(enity.getCheckInEntity().getCheckindate())) {
				checkInRequest = enity.getCheckInEntity();
			} else {
				// for 金旭未帶guest相關資訊
				List<GuestRequest> guestlist = GuestManager.getInstance().findGuestInfo(enity.getOldRoomNumber());
				GuestRequest guest = null;
				for (GuestRequest guestRequest : guestlist) {
					guest = new GuestRequest(guestRequest.getGuestno(),
							enity.getCheckInEntity().getGuests().get(0).getFirstname(), guestRequest.getLastname(),
							guestRequest.getBirthd(), guestRequest.getDepdate(), guestRequest.getGroupid(),
							guestRequest.getSalutation());
				}
				String chki_time = BillManager.getInstance().findBillCheckinDate(enity.getCheckInEntity().getBillno(),
						enity.getOldRoomNumber());
				guestlist.clear();
				guestlist.add(guest);
				checkInRequest = new CheckInRequest(enity.getNewRoomNumber(), chki_time, guestlist, "",
						enity.getCheckInEntity().getBillno(), enity.getCheckInEntity().getTvright(),
						enity.getCheckInEntity().getGuestReturn());
			}
			// new CheckInProcess(checkInRequest).check();
		}
		LogAction.getInstance().debug("Step 1/" + STEP + " check in request=" + enity + ".");

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

		RCUServiceConfiguration rcuConfig = null;
		try {
			BlackcoreConfiguration configure = (BlackcoreConfiguration) DataCenter.getInstance()
					.get(SETTING.CONFIGURATION);
			rcuConfig = Configuration.readRCUServiceConfiguration(new File(Env.SYSTEM_DEF_PATH + configure.getRcu()));
			LogAction.getInstance().debug("Step 5/" + STEP + " set welcome message success.");
		} catch (Exception e) {
			LogAction.getInstance().debug("RCU path:" + e);
		}

		if (rcuConfig.isEnable()) {
			try {
				// RCU Command
				new RoomModeProcess(new RcuRoomMode(enity.getOldRoomNumber(), RCUMode.CHECKOUT)).execute();
				LogAction.getInstance().debug("RCU Check Out command success");
			} catch (Exception e) {
				LogAction.getInstance().debug("RCU Check Out command : " + e);
			}
		}
		LogAction.getInstance().debug("step STB reboot and check out success");

		return null;
	}
}
