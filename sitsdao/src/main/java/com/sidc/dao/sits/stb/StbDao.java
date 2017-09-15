package com.sidc.dao.sits.stb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbDao {

	private StbDao() {
	}

	private static final class lazyHolder {
		public static final StbDao INSTANCE = new StbDao();
	}

	public static StbDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private static final String SELECT_STB_IP = "SELECT ip FROM stb WHERE room_no = ?";

	public ArrayList<String> selectStbIp(final Connection conn, final String roomNo) throws SQLException {

		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_STB_IP);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("ip"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private static final String IS_EXISTED = "SELECT ip FROM stb WHERE room_no = ? AND ip = ? ;";

	public boolean isExisted(final Connection conn, final String roomNo, final String stbIp) throws SQLException {

		boolean isPass = false;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_EXISTED);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setString(++i, stbIp);

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

	private static final String SELECT_IP = "SELECT ip FROM stb WHERE room_no = ? LIMIT 1;";

	public String selectSingleStbIp(final Connection conn, final String roomNo) throws SQLException {

		String ip = null;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_IP);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				ip = rs.getString("ip");
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return ip;
	}

}
