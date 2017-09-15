package com.sidc.dao.sits.pincode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PinCodeDao {

	private static final class lazyHolder {
		public static PinCodeDao INSTANCE = new PinCodeDao();
	}

	public static PinCodeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO pin_code(pc_id,pc_roomno,pc_code,pc_creation_time) VALUES(?,?,?,NOW());";

	public String insert(final Connection conn, final String roomNo, final String code) throws SQLException {

		PreparedStatement psmt = null;
		final String uuid = UUID.randomUUID().toString();
		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, uuid);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, code);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return uuid;
	}

	private final static String SELECT_BY_PINCODE = "SELECT pc_code FROM pin_code WHERE pc_code = ?;";

	public boolean isExist(final Connection conn, final String pincode) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_PINCODE);

			int i = 0;
			psmt.setString(++i, pincode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String DELETE_BY_ROOMNO = "DELETE FROM pin_code WHERE pc_roomno = ?;";

	public void delect(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, roomNo);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_ROOMNO_BY_PINCODE = "SELECT pc_roomno FROM pin_code WHERE pc_code = ? "
			+ "ORDER BY pc_creation_time DESC LIMIT 1 ;";

	public String selectRoomNoByPinCode(final Connection conn, final String pincode) throws SQLException {

		PreparedStatement psmt = null;
		String roomNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_ROOMNO_BY_PINCODE);

			int i = 0;
			psmt.setString(++i, pincode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				roomNo = rs.getString("pc_roomno");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return roomNo;
	}

}
