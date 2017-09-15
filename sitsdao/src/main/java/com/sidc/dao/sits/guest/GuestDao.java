package com.sidc.dao.sits.guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sidc.blackcore.api.agent.request.GuestRequest;
import com.sidc.blackcore.api.mobile.guest.bean.GuestInfoBean;

public class GuestDao {
	/**
	 * @author Joe
	 */
	private GuestDao() {
	}

	private static class LazyHolder {
		public static final GuestDao INSTANCE = new GuestDao();
	}

	public static GuestDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SEARCH_GUEST = "SELECT id FROM guest WHERE guest_no = ? ";

	public List<String> searchByGuestNo(final Connection conn, final String guestNo) throws SQLException {
		List<String> al = new ArrayList<String>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_GUEST);

			int i = 0;
			psmt.setString(++i, guestNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				al.add(rs.getString("id"));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return al;
	}

	private final static String UPDATE_CHECK_INFO = "UPDATE guest SET bill_no = ?, room_no = ?, en_first_name = ?, "
			+ "en_last_name = ?, `national` = ?, birth_month = ?, birth_day = ?, salutation = ?, departure_date = ?, "
			+ "last_modify = now() WHERE id = ?";

	public void updateWithCheckIn(final Connection conn, final String billNo, final String guestID, final String roomNo,
			final String fistName, final String lastName, final int birthMonth, final int birthDay, final String lang,
			final String salutation, final String depDate) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_CHECK_INFO);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, fistName);
			psmt.setString(++i, lastName);
			psmt.setString(++i, lang);
			psmt.setInt(++i, birthMonth);
			psmt.setInt(++i, birthDay);
			psmt.setString(++i, salutation);
			psmt.setString(++i, depDate);
			psmt.setString(++i, guestID);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String INSERT_WITH_CHECKIN = "INSERT INTO guest(id, guest_no, bill_no, room_no, en_first_name, "
			+ "en_last_name, birth_month, birth_day, departure_date, national, salutation, last_modify) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";

	public void insertWithCheckIn(final Connection conn, final String roomNo, final String billNo, final String guestNo,
			final String firstName, final String lastName, final int birth_month, final int birth_day,
			final String depDate, final String lang, final String salutation) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_WITH_CHECKIN);

			int i = 0;
			psmt.setString(++i, UUID.randomUUID().toString().replace("-", ""));
			psmt.setString(++i, UUID.randomUUID().toString().replace("-", ""));
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, firstName);
			psmt.setString(++i, lastName);
			psmt.setInt(++i, birth_month);
			psmt.setInt(++i, birth_day);
			psmt.setString(++i, depDate);
			psmt.setString(++i, lang);
			psmt.setString(++i, salutation);

			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	/**
	 * 
	 * @author Allen Huang
	 *
	 */
	private final static String DELETE_GUEST = "DELETE FROM guest WHERE bill_no = ? AND room_no = ?";

	public void deleteGuest(final Connection conn, final String billNo, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_GUEST);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);

			psmt.executeUpdate();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_GUEST_BY_ROOMNO = "DELETE FROM guest WHERE room_no = ?";

	public void deleteGuestByRoomNo(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_GUEST_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, roomNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String FIND_WITH_ROOMCHANGE = "SELECT guest_no, en_first_name, en_last_name,"
			+ "Concat('1911/',birth_month,'/',birth_day) AS birthday, DATE_FORMAT(departure_date, '%Y/%m/%d') AS dep_date, "
			+ "national, salutation FROM guest WHERE room_no = ?";

	public List<GuestRequest> findWithRoomChange(final Connection conn, final String roomNo) throws SQLException {

		List<GuestRequest> guestList = new ArrayList<GuestRequest>();
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(FIND_WITH_ROOMCHANGE);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				GuestRequest guestEntity = new GuestRequest(rs.getString("guest_no"), rs.getString("en_first_name"),
						rs.getString("en_last_name"), rs.getString("birthday"), rs.getString("dep_date"),
						rs.getString("salutation"));
				guestList.add(guestEntity);
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return guestList;
	}

	private final static String SELECT_ROOM_LIST = "SELECT DISTINCT(room_no) FROM guest WHERE group_id = ? ORDER BY room_no ASC;";

	public List<String> selectRoomList(final Connection conn, final String groupId) throws SQLException {

		List<String> list = new ArrayList<String>();
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(SELECT_ROOM_LIST);

			int i = 0;
			psmt.setString(++i, groupId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("room_no"));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_GUESTINFO = "SELECT guest_no,en_first_name,en_last_name,"
			+ "group_id,national,salutation FROM guest WHERE room_no = ?";

	public List<GuestInfoBean> selectGuestInfo(final Connection conn, final String roomNo) throws SQLException {

		List<GuestInfoBean> guestList = new ArrayList<GuestInfoBean>();
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(SELECT_GUESTINFO);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				guestList.add(new GuestInfoBean(rs.getString("guest_no"),
						rs.getString("en_first_name") + " " + rs.getString("en_last_name"), rs.getString("group_id"),
						rs.getString("national"), rs.getString("salutation")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return guestList;
	}

	public GuestInfoBean selectGuest(final Connection conn, final String roomNo, final String guestNo)
			throws SQLException {

		GuestInfoBean entity = null;
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(SELECT_GUESTINFO);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new GuestInfoBean(rs.getString("guest_no"),
						rs.getString("en_first_name") + " " + rs.getString("en_last_name"), rs.getString("group_id"),
						rs.getString("national"), rs.getString("salutation"));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String SELECT_BY_ID_GUESTNO = "SELECT guest_no FROM guest WHERE guest_no = ? AND room_no = ?;";

	public boolean selectByIdGuestNo(final Connection conn, final String roomNo, final String guestNo)
			throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_GUESTNO);

			int i = 0;
			psmt.setString(++i, guestNo);
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

	private final static String IS_OVER_DEPARTUREDATE = "SELECT CASE WHEN departure_date IS NULL THEN 0 WHEN DATE_FORMAT(?,"
			+ "'%Y-%m-%d') <= departure_date THEN 0 ELSE 1 END AS isOver FROM guest WHERE guest_no = ? AND room_no = ? ;";

	public boolean isOverDepartureDate(final Connection conn, final String roomNo, final String guestNo,
			final String date) throws SQLException {

		PreparedStatement psmt = null;
		boolean isOver = true;
		try {
			psmt = conn.prepareStatement(IS_OVER_DEPARTUREDATE);

			int i = 0;
			psmt.setString(++i, date);
			psmt.setString(++i, guestNo);
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isOver = rs.getBoolean("isOver");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isOver;
	}

	private final static String IS_OVER_DEPARTUREDATE_BY_ROOM = "SELECT CASE WHEN departure_date IS NULL THEN 0 WHEN DATE_FORMAT(?,"
			+ "'%Y-%m-%d') <= departure_date THEN 0 ELSE 1 END AS isOver FROM guest WHERE bill_no = (SELECT bill_no FROM bill WHERE "
			+ "room_no = ? AND chko_time IS NULL ORDER BY chki_time DESC LIMIT 1) ORDER BY departure_date ASC LIMIT 1;";

	public boolean isOverDepartureDate(final Connection conn, final String roomNo, final String date)
			throws SQLException {

		PreparedStatement psmt = null;
		boolean isOver = true;
		try {
			psmt = conn.prepareStatement(IS_OVER_DEPARTUREDATE_BY_ROOM);

			int i = 0;
			psmt.setString(++i, date);
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isOver = rs.getBoolean("isOver");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isOver;
	}

	private final static String SELECT_GROUPID = "SELECT DISTINCT group_id FROM guest WHERE group_id IS NOT NULL AND bill_no IN ";

	public List<String> listGroupId(final Connection conn, final String billNoWhereIn) throws SQLException {

		PreparedStatement psmt = null;
		List<String> result = new ArrayList<String>();
		try {
			psmt = conn.prepareStatement(SELECT_GROUPID + billNoWhereIn);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("group_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return result;
	}
}
