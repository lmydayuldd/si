package com.sidc.dao.sits.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.blackcore.api.sits.room.bean.PayServiceBean;
import com.sidc.dao.sits.bean.RoomBean;
import com.sidc.quartz.api.request.CheckOutRoomScheduleListRequest;
import com.sidc.quartz.api.response.CheckOutRoomScheduleResposne;

public class RoomDao {
	/**
	 * @author Joe
	 */
	private RoomDao() {
	}

	private static class LazyHolder {
		public static final RoomDao INSTANCE = new RoomDao();
	}

	public static RoomDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 
	 * @author Allen Huang
	 *
	 */
	private final static String EXIST_ROOM_NO = "SELECT no AS total FROM room WHERE no = ? AND bill_no != '' ";

	public boolean existRoomNo(final Connection conn, final String roomNo) throws SQLException {
		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(EXIST_ROOM_NO);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isPass = true;
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String UPDATE_WITH_CHICK_IN = "UPDATE ROOM SET bill_no = ?, folio = ?, new_msg = 0, "
			+ "welcome_msg = ?, parent_control = ?, p_password = ?, adult_warning = ?, pay_service = ?, "
			+ "message_light = ?, block_code = ? WHERE no = ?";

	public void updateWithCheckIn(final Connection conn, final String roomNo, final String billNo,
			final String adultWarning, final Short payService) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_WITH_CHICK_IN);
			RoomBean room = new RoomBean((short) 0);
			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, "");
			psmt.setShort(++i, room.getWelcomeMsg());
			psmt.setShort(++i, room.getParentControl());
			psmt.setString(++i, "");
			psmt.setString(++i, adultWarning);
			psmt.setShort(++i, payService);
			psmt.setBoolean(++i, room.isMessageLight());
			psmt.setString(++i, room.getBlock_code());
			psmt.setString(++i, roomNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SEARCH_WECLOME_MSG = "SELECT welcome_msg FROM room WHERE no = ?";

	public short searchWelcomeMsg(final Connection conn, final String roomNo) throws SQLException {
		PreparedStatement psmt = null;
		short item = -9999;
		try {
			psmt = conn.prepareStatement(SEARCH_WECLOME_MSG);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				item = Short.valueOf(rs.getString("welcome_msg"));
			} else {
				new SQLException();
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return item;
	}

	/**
	 * 
	 * @author Allen Huang
	 *
	 */
	private final static String UPDATE_WITH_CHECK_OUT = "UPDATE room SET bill_no = ?, folio = ?, "
			+ "block_code = ?, new_msg = ?, welcome_msg = ?, parent_control = ?, p_password = ?, "
			+ "adult_warning = ?, pay_service = ?, av_passcode = ? WHERE no = ?";

	public void checkOut(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_WITH_CHECK_OUT);

			int i = 0;
			psmt.setString(++i, "");
			psmt.setString(++i, "");
			psmt.setString(++i, "");
			psmt.setInt(++i, 0);
			psmt.setShort(++i, Room.WELCOME_FIRST_TIME);
			psmt.setShort(++i, Room.UNLOCK);
			psmt.setString(++i, Room.PASSWORD_ADULT_WARNING);
			psmt.setString(++i, Room.UNWARN);
			psmt.setShort(++i, Room.PAY_OFF);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, roomNo);

			psmt.executeUpdate();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_BILL = "SELECT bill_no FROM room WHERE no = ? AND bill_no IS NOT NULL";

	public String selectByBill(final Connection conn, final String roomNo) throws SQLException {

		String billNo = "";
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_BILL);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				billNo = rs.getString("bill_no");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return billNo;
	}

	private final static String SEARCH_WITH_CHECKIN_INFO = "SELECT R.no, R.bill_no, "
			+ "DATE_FORMAT(B.chki_time, '%Y/%m/%d') AS chki_time, national "
			+ "FROM room R LEFT JOIN bill B ON R.bill_no = B.bill_no AND B.chko_time IS NULL "
			+ "LEFT JOIN guest G ON R.no = G.room_no AND B.bill_no = G.bill_no WHERE R.no = ?";

	public CheckInRequest searchWithCheckInfo(final Connection conn, final String oldRoomNo, final String newRoomNo)
			throws SQLException {
		PreparedStatement psmt = null;
		CheckInRequest entity = null;
		try {
			psmt = conn.prepareStatement(SEARCH_WITH_CHECKIN_INFO);

			int i = 0;
			psmt.setString(++i, oldRoomNo);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				entity = new CheckInRequest(newRoomNo, rs.getString("chki_time"), null, rs.getString("national"),
						rs.getString("bill_no"), null);
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String FIND_WITH_CHECKOUT_STATUS = "SELECT chko_time FROM bill  WHERE room_no = ? "
			+ "ORDER BY chki_time DESC LIMIT 1";

	public String findWithCheckOutStatus(final Connection conn, final String roomNo) throws SQLException {
		String checkOutDate = null;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(FIND_WITH_CHECKOUT_STATUS);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				checkOutDate = rs.getString("chko_time");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return checkOutDate;
	}

	private final static String IS_CHECKIN = "select count(bill_no) as total  from bill "
			+ "where (chko_time is null OR chko_time = '') and chki_time < now() and room_no = ?";

	public boolean isCheckIn(final Connection conn, final String roomNo) throws SQLException {
		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_CHECKIN);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				int total = rs.getInt("total");
				isPass = (total >= 1);
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String UPDATE_NEW_MESSAGE = "UPDATE ROOM SET new_msg = ? WHERE no = ?";

	public void updateNewMessage(final Connection conn, final String roomNo) throws SQLException {

		final int STATUS_NEW_MESSAGE = 1;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_NEW_MESSAGE);
			int i = 0;
			psmt.setInt(++i, STATUS_NEW_MESSAGE);
			psmt.setString(++i, roomNo);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_ROOMS = "select no from room";

	public List<String> listRoom(final Connection conn) throws SQLException {

		List<String> rooms = new ArrayList<String>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_ROOMS);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				rooms.add(rs.getString("no"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return rooms;
	}

	private final static String LIST_ROOMS_STATUS_CHECKIN = "select no, (select count(bill_no) from bill "
			+ "where (chko_time is null OR chko_time = '') and chki_time < now() and room_no = A.no) AS total "
			+ "from room A order by A.no * 1 asc ;";

	public Map<String, Boolean> listRoomStatusofCheckIn(final Connection conn) throws SQLException {

		Map<String, Boolean> result = new HashMap<String, Boolean>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_ROOMS_STATUS_CHECKIN);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				int total = rs.getInt("total");

				result.put(rs.getString("no"), (total >= 1));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return result;
	}

	private final static String IS_IDLEROOM = "SELECT no FROM room WHERE bill_no = ''";

	public List<String> isIdleRoom(final Connection conn) throws SQLException {

		List<String> rooms = new ArrayList<String>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_IDLEROOM);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				rooms.add(rs.getString("no"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return rooms;
	}

	public List<String> listRoomOfCheckIn(final Connection conn) throws SQLException {

		List<String> result = new ArrayList<String>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_ROOMS_STATUS_CHECKIN);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("total") >= 1) {
					result.add(rs.getString("no"));
				}
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return result;
	}

	private final static String SELECT_BY_ROOMNO = "SELECT no FROM room WHERE no = ? ;";

	public boolean isExist(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, roomNo);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isExist = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}

	private final static String SELECT_ROOMNO_BILLNO = "SELECT no FROM room WHERE no = ? AND bill_no = ?";

	public boolean isExist(final Connection conn, final String roomNo, final String billNo) throws SQLException {

		boolean isExist = false;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_ROOMNO_BILLNO);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setString(++i, billNo);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isExist = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}

	private final static String SELECT_BY_FLOOR = "SELECT no FROM room WHERE floor = ?;";

	public List<String> selecrRoomNoByFloor(final Connection conn, final String floor) throws SQLException {

		PreparedStatement psmt = null;
		List<String> list = new ArrayList<String>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_FLOOR);

			int i = 0;
			psmt.setString(++i, floor);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("no"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String LIST_CHECKOUT_ROOM = "SELECT A.no, B.ip FROM room A LEFT JOIN stb B ON A.no = B.room_no "
			+ "WHERE A.bill_no = '' OR A.bill_no IS NULL";

	public CheckOutRoomScheduleListRequest listCheckOutRoom(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		CheckOutRoomScheduleListRequest response = null;
		List<CheckOutRoomScheduleResposne> list = new ArrayList<CheckOutRoomScheduleResposne>();
		try {
			psmt = conn.prepareStatement(LIST_CHECKOUT_ROOM);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new CheckOutRoomScheduleResposne(rs.getString("no"), rs.getString("ip")));
			}
			response = new CheckOutRoomScheduleListRequest(list);

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return response;
	}

	public List<String> listCheckOutRooms(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		List<String> list = new ArrayList<String>();
		try {
			psmt = conn.prepareStatement(LIST_CHECKOUT_ROOM);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("no"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SELECT_PAYSERVICE = "SELECT pay_service,adult_warning FROM room WHERE no = ? ";

	public PayServiceBean selectPayServiceInfo(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		PayServiceBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_PAYSERVICE);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new PayServiceBean(rs.getString("pay_service").equals("1") ? true : false,
						rs.getString("adult_warning").equals("1") ? true : false);
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return entity;
	}

	private final static String UPDATE_TVRIGHT = "UPDATE room SET pay_service = ?, adult_warning = ? WHERE no = ?";

	public void updateTvright(final Connection conn, final Short payService, final String adultWarning,
			final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_TVRIGHT);

			int i = 0;
			psmt.setShort(++i, payService);
			psmt.setString(++i, adultWarning);
			psmt.setString(++i, roomNo);

			psmt.executeUpdate();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
