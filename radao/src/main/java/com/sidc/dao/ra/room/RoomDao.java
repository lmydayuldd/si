/**
 * 
 */
package com.sidc.dao.ra.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nandin
 *
 */
public class RoomDao {

	private RoomDao() {
	}

	private static class LazyHolder {
		public static final RoomDao INSTANCE = new RoomDao();
	}

	public static RoomDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_BILL = "SELECT bill_no FROM room WHERE no = ? AND bill_no IS NOT NULL AND bill_no != '';";

	public String findBillNo(final Connection conn, final String roomNo) throws SQLException {

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

}
