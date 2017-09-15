package com.sidc.dao.sits.roomallocation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomAllocationDao {
	/**
	 * @author Joe
	 */
	private RoomAllocationDao() {
	}

	private static class LazyHolder {
		public static final RoomAllocationDao INSTANCE = new RoomAllocationDao();
	}

	public static RoomAllocationDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String EXIST = "SELECT ra_roomno FROM room_allocation WHERE ra_roomno = ? AND ra_belong_id = ? AND ra_type = ? ";

	public boolean isExist(final Connection conn, final String roomNo, final String staffId, final int type)
			throws SQLException {
		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(EXIST);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setString(++i, staffId);
			psmt.setInt(++i, type);

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

	private final static String SELECT_BELONGID = "SELECT ra_belong_id FROM room_allocation WHERE ra_roomno = ? AND ra_type = ? ";

	public List<String> selectBelongId(final Connection conn, final String roomNo, final int type) throws SQLException {

		PreparedStatement psmt = null;
		List<String> list = new ArrayList<String>();
		try {
			psmt = conn.prepareStatement(SELECT_BELONGID);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setInt(++i, type);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("ra_belong_id"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String INSERT = "INSERT INTO room_allocation (ra_roomno,ra_belong_id,ra_type) VALUES (?,?,?)";

	public void insert(final Connection conn, final String roomNo, final String belongId, final int type)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);
			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setString(++i, belongId);
			psmt.setInt(++i, type);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM room_allocation WHERE ra_belong_id = ? AND ra_type = ?  ";

	public void delete(final Connection conn, final String belongId, final int type) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setString(++i, belongId);
			psmt.setInt(++i, type);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_ALLOACTIONID = "SELECT DISTINCT ra_belong_id,(SELECT COUNT(*) FROM room_allocation ra2 "
			+ "WHERE ra.ra_belong_id = ra2.ra_belong_id) AS total FROM room_allocation ra WHERE ra_type = ? ORDER BY total ASC LIMIT 1;";

	public String selectAllocationId(final Connection conn, final int type) throws SQLException {

		PreparedStatement psmt = null;
		String id = null;
		try {
			psmt = conn.prepareStatement(SELECT_ALLOACTIONID);

			int i = 0;
			psmt.setInt(++i, type);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("ra_belong_id");
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_STAFFID = "SELECT tsd_staff_id ,(SELECT COUNT(*) FROM room_allocation "
			+ "WHERE ra_belong_id = tsd_staff_id) AS total FROM token_header th LEFT JOIN token_staff_detail "
			+ "tsf ON th.th_id = tsf.tsd_token_header_id WHERE th.th_type = ? AND NOW() < th.th_expired_time ORDER BY total ASC LIMIT 1;";

	public String getAllocationStaffId(final Connection conn, final int type) throws SQLException {
		PreparedStatement psmt = null;

		String id = null;

		try {
			psmt = conn.prepareStatement(SELECT_STAFFID);

			int i = 0;
			psmt.setInt(++i, type);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getString("tsd_staff_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_ROOMNO = "SELECT ra_roomno FROM room_allocation WHERE ra_belong_id = ? AND ra_type = 1;";

	public List<String> selectRoomNo(final Connection conn, final String belongId) throws SQLException {

		PreparedStatement psmt = null;
		List<String> roomList = new ArrayList<String>();
		try {
			psmt = conn.prepareStatement(SELECT_ROOMNO);

			int i = 0;
			psmt.setString(++i, belongId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				roomList.add(rs.getString("ra_roomno"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return roomList;
	}

}
