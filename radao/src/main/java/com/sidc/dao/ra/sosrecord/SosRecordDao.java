package com.sidc.dao.ra.sosrecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SosRecordDao {

	private SosRecordDao() {
	}

	private static class LazyHolder {
		public static final SosRecordDao INSTANCE = new SosRecordDao();
	}

	public static SosRecordDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO sos_record(sr_roomno,sr_billno,sr_action,sr_client_role,sr_client_ip,sr_creation_time) "
			+ " VALUES(?,?,?,?,?,NOW())";

	public int insert(Connection conn, final String roomNo, final String billNo, final String action,
			final String ipAddress, final String clientRole) throws SQLException {

		PreparedStatement psmt = null;
		int id = -9999;
		try {

			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setString(++i, billNo);
			psmt.setString(++i, action);
			psmt.setString(++i, clientRole);
			psmt.setString(++i, ipAddress);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("sos_record insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}
}
