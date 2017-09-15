
package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.blackcore.api.agent.request.GuestRequest;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.room.Room;
import com.sidc.dao.sits.room.RoomDao;

/**
 * @author Joe
 *
 */
public class CheckInManager {

	private CheckInManager() {
	}

	private static class LazyHolder {
		public static final CheckInManager INSTANCE = new CheckInManager();
	}

	public static CheckInManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	/*
	 * step1 取得 bill no step2 初始化room房間資訊 step3 初始化guest資訊
	 * 
	 * -9999 system error 0 success
	 * 
	 */

	public void checkIn(final CheckInRequest enity, final String pinCode) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// 2017/05/25 新增 pincode需求
			doCheckIn(conn, enity, pinCode);

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void doCheckIn(final Connection conn, final CheckInRequest enity, final String pincode) throws SQLException {
		try {
			final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			String billNo = enity.getBillno();

			GuestDao.getInstance().deleteGuestByRoomNo(conn, enity.getRoomno());

			if (StringUtils.isBlank(enity.getCheckindate())) {
				throw new NullPointerException("checkin date");
			}

			String checkinDate = enity.getCheckindate();

			if (!isDate(enity.getCheckindate(), "yyyy/MM/dd HH:mm:ss")) {
				checkinDate += " " + new Date().getHours() + ":" + new Date().getMinutes() + ":"
						+ new Date().getSeconds();
			}

			// 不同廠商 可能會帶入自己的帳單 可能不會
			if (!StringUtils.isBlank(billNo)) {
				/**
				 * 與金旭測試發現，他們會發兩次以上check in，所以不先砍掉的話會出現 sql exception 2016/11/18
				 * 有bill no 又不存在在DB中 就給他一筆 存在在DB更新就好
				 */
				if (!BillDao.getInstance().searchBillNoWithBillNo(conn, billNo)) {
					BillDao.getInstance().insert(conn, enity.getRoomno(), billNo, checkinDate);
				} else {

					// 改成update 帳單 才不會錯亂 2016/12/30
					BillDao.getInstance().updateWithBillNo(conn, enity.getRoomno(), billNo, checkinDate);
				}
			} else {
				// 沒傳 就自己新增一筆
				billNo = BillDao.getInstance().insert(conn, enity.getRoomno(), checkinDate);
			}

			// 電影觀看權限
			String adultWarning = Room.UNWARN;
			Short payService = Room.PAY_ON;
			if (StringUtils.isNotBlank(enity.getTvright())) {
				// 全部不可看
				if (enity.getTvright().equals("TM")) {
					adultWarning = Room.WARNED;
					payService = Room.PAY_OFF;
				}
				// 限制級不可看
				if (enity.getTvright().equals("TX")) {
					adultWarning = Room.WARNED;
				}
			}

			RoomDao.getInstance().updateWithCheckIn(conn, enity.getRoomno(), billNo, adultWarning, payService);

			for (GuestRequest guestEntity : enity.getGuests()) {
				final List<String> guestList = GuestDao.getInstance().searchByGuestNo(conn, guestEntity.getGuestno());

				// DB 吃的是日期是數字格式...0代表沒帶入
				Date birthday = null;
				int month = 0;
				int day = 0;

				if (!StringUtils.isBlank(guestEntity.getBirthd())) {
					birthday = formatter.parse(guestEntity.getBirthd());
					month = birthday.getMonth() + 1;
					day = birthday.getDate();
				}

				// 2017/06/28 新增 預計 check out 需求
				if (guestList.isEmpty()) {
					GuestDao.getInstance().insertWithCheckIn(conn, enity.getRoomno(), billNo, guestEntity.getGuestno(),
							guestEntity.getFirstname(), guestEntity.getLastname(), month, day,
							guestEntity.getDepdate(), enity.getLangcode(),
							guestEntity.getGender());
				} else {
					for (String guestID : guestList) {
						GuestDao.getInstance().updateWithCheckIn(conn, billNo, guestID, enity.getRoomno(),
								guestEntity.getFirstname(), guestEntity.getLastname(), month, day, enity.getLangcode(),
								guestEntity.getGender(), guestEntity.getDepdate());
					}
				}
			}

			// 2017/05/25 新增 pin code需求
			// pin code process
			// 刪掉舊有的 避免 舊房客繼續用
			// 老闆遲遲為決定要不要用 先停
			// PinCodeDao.getInstance().delect(conn, enity.getRoomno());
			// PinCodeDao.getInstance().insert(conn, enity.getRoomno(),
			// pincode);

		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	/*
	 * get welcome_msg
	 */
	public short searchRoom(final String roomNo) throws SQLException {
		short welcomeMsgCount = 0;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			welcomeMsgCount = RoomDao.getInstance().searchWelcomeMsg(conn, roomNo);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return welcomeMsgCount;
	}

	public int searchRoomWithCheckInInfo(final String roomNo) throws SQLException {
		short welcomeMsgCount = 0;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			welcomeMsgCount = RoomDao.getInstance().searchWelcomeMsg(conn, roomNo);
			RoomDao.getInstance().checkOut(conn, roomNo);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return welcomeMsgCount;
	}

	public boolean findRoom(final String roomNo) throws SQLException {
		boolean isPass = false;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			isPass = RoomDao.getInstance().findRoomNoByRoomNo(conn, roomNo);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public String findRoomCheckOutStatus(final String roomNo) throws SQLException {
		String checkOutDate = null;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			checkOutDate = RoomDao.getInstance().findWithCheckOutStatus(conn, roomNo);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return checkOutDate;
	}

	public boolean checkBillNoRoomno(final String billNo, final String roomNo) throws SQLException {
		boolean isPass = false;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			isPass = BillDao.getInstance().searchBillnoByBillnoRoomno(conn, billNo, roomNo);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	private boolean isDate(final String dttm, final String format) {
		final DateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(false);
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(dttm, pos);

		if (date == null || pos.getErrorIndex() > 0) {
			return false;
		}
		if (pos.getIndex() != dttm.length()) {
			return false;
		}

		if (formatter.getCalendar().get(Calendar.YEAR) > 9999) {
			return false;
		}
		return true;
	}
}
