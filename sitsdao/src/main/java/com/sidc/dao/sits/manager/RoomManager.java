/**
 * 
 */
package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.room.response.RoomCheckInInfoResponse;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.roomallocation.RoomAllocationDao;
import com.sidc.dao.sits.tokenheader.TokenHeaderDao;
import com.sidc.dao.sits.tokenroomdetail.TokenRoomDetailDao;
import com.sidc.quartz.api.request.CheckOutRoomScheduleListRequest;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class RoomManager {

	private RoomManager() {
	}

	private static class LazyHolder {
		public static final RoomManager INSTANCE = new RoomManager();
	}

	public static RoomManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public boolean isCheckin(final String roomNo) throws SQLException {
		boolean isPass = false;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = RoomDao.getInstance().isCheckIn(conn, roomNo);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return isPass;
	}

	public void updateNewMessage(final String roomNo) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RoomDao.getInstance().updateNewMessage(conn, roomNo);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

	}

	public List<String> listRoom() throws SQLException {

		List<String> result = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RoomDao.getInstance().listRoom(conn);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;

	}

	public Map<String, Boolean> listRoomStatusCheckin() throws SQLException {
		Map<String, Boolean> result = new HashMap<String, Boolean>();

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RoomDao.getInstance().listRoomStatusofCheckIn(conn);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}

	public List<String> listIdleRoom() throws SQLException {

		List<String> rooms = new ArrayList<String>();

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			rooms = RoomDao.getInstance().isIdleRoom(conn);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return rooms;
	}

	public void roomAllocation(final List<String> roomList, final String deviceId, final int type)
			throws SQLException, SiDCException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RoomAllocationDao.getInstance().delete(conn, deviceId, type);

			for (final String roomNo : roomList) {
				if (!RoomDao.getInstance().findRoomNoByRoomNo(conn, roomNo)) {
					throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find room no. " + roomNo);
				}
				RoomAllocationDao.getInstance().insert(conn, roomNo, deviceId, type);
			}

			conn.commit();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	// 分配文字訊息部分
	public void roomAllocationWithChat(final List<String> roomList) throws SQLException, SiDCException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String roomNo : roomList) {
				if (!RoomAllocationDao.getInstance().selectBelongId(conn, roomNo, 1).isEmpty()) {
					continue;
				}

				final String staffId = RoomAllocationDao.getInstance().getAllocationStaffId(conn, 1);

				if (StringUtils.isBlank(staffId)) {
					throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "no staff online.");
				}
				RoomAllocationDao.getInstance().insert(conn, roomNo, staffId, 1);
			}

			conn.commit();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public List<String> findChatHandler(final String roomNo) throws SQLException, SiDCException {
		Connection conn = null;
		List<String> list = new ArrayList<String>();

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RoomAllocationDao.getInstance().selectBelongId(conn, roomNo, 1);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public String findBillno(final String roomNo) throws SQLException, SiDCException {

		String billNo = "";
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			billNo = RoomDao.getInstance().selectByBill(conn, roomNo);
			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return billNo;
	}

	public void transferChatHandler(final List<String> roomList, final String userId, final String targetId)
			throws SQLException, SiDCException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (TokenHeaderDao.getInstance().checkExpiredWithChat(conn, 1, targetId)) {
				throw new SiDCException(APIStatus.STAFF_OFFLINE, targetId + " staff not online.");
			}

			for (final String roomNo : roomList) {
				if (!RoomAllocationDao.getInstance().isExist(conn, roomNo, userId, 1)) {
					throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST,
							"room no :" + roomNo + " not belong " + userId);
				}
			}

			RoomAllocationDao.getInstance().delete(conn, userId, 1);

			for (final String roomNo : roomList) {
				RoomAllocationDao.getInstance().insert(conn, roomNo, targetId, 1);
			}

			conn.commit();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public List<String> listRoomWithCheckIn() throws SQLException {

		List<String> result = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RoomDao.getInstance().listRoomOfCheckIn(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return result;
	}

	public boolean isExist(final List<String> list) throws SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String roomNo : list) {
				isExist = RoomDao.getInstance().isExist(conn, roomNo);
				if (!isExist) {
					return false;
				}
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isExist;
	}

	public List<String> listRoomByFloor(final List<String> list) throws SQLException {

		List<String> result = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String floor : list) {
				result.addAll(RoomDao.getInstance().selecrRoomNoByFloor(conn, floor));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return result;
	}

	public CheckOutRoomScheduleListRequest listCheckOutRoom() throws SQLException {

		CheckOutRoomScheduleListRequest list = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RoomDao.getInstance().listCheckOutRoom(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return list;
	}

	public boolean isCheckInByDevice(final String device) throws SQLException {

		Connection conn = null;
		boolean isCheckIn = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String roomNo = TokenRoomDetailDao.getInstance().selectRoomNo(conn, device);

			isCheckIn = RoomDao.getInstance().isCheckIn(conn, roomNo);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isCheckIn;
	}

	/**
	 * 取得所有check in room,還有 group id
	 * 
	 * @return
	 * @throws SQLException
	 */
	public RoomCheckInInfoResponse listRoomAndGroupOfCheckIn() throws SQLException {

		RoomCheckInInfoResponse entity = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final List<String> roomList = RoomDao.getInstance().listRoomOfCheckIn(conn);

			String billNoWhereIn = "(";

			for (final String roomNo : roomList) {
				final String billNo = BillDao.getInstance().searchBillNoWithRoomNo(conn, roomNo);
				billNoWhereIn += "'" + billNo + "',";
			}

			billNoWhereIn = billNoWhereIn.substring(0, billNoWhereIn.length() - 1) + ")";

			final List<String> groupList = GuestDao.getInstance().listGroupId(conn, billNoWhereIn);

			entity = new RoomCheckInInfoResponse(roomList, groupList);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}
}
