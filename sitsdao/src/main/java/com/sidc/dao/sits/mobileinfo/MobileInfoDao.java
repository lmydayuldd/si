package com.sidc.dao.sits.mobileinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MobileInfoDao {
	private MobileInfoDao() {
	}

	private static class LazyHolder {
		public static final MobileInfoDao INSTANCE = new MobileInfoDao();
	}

	public static MobileInfoDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO mobile_info(mi_device,mi_operating_system,mi_version,mi_push_token,mi_user_name,mi_creation_time)"
			+ "VALUES(?,?,?,?,?,NOW())";

	public int insert(final Connection conn, final String device, final int operatingSystem, final String version,
			final String pushToken, final String userName) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, device);
			psmt.setInt(++i, operatingSystem);
			psmt.setString(++i, version);
			psmt.setString(++i, pushToken);
			psmt.setString(++i, userName);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE mobile_info SET mi_version = ? , mi_push_token = ?,mi_user_name = ? WHERE mi_id = ? ;";

	public void update(final Connection conn, final int mobileInfoId, final String version, final String pushToken,
			final String userName) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;

			psmt.setString(++i, version);
			psmt.setString(++i, pushToken);
			psmt.setString(++i, userName);
			psmt.setInt(++i, mobileInfoId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_NAME = "UPDATE mobile_info SET mi_user_name = ? WHERE mi_id = ? ;";

	public void updateName(final Connection conn, final int mobileInfoId, final String userName) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_NAME);

			int i = 0;

			psmt.setString(++i, userName);
			psmt.setInt(++i, mobileInfoId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT = "SELECT mi_id FROM mobile_info WHERE mi_device = ? AND mi_operating_system = ? ;";

	public int getId(final Connection conn, final String device, final int operatingSystem) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, device);
			psmt.setInt(++i, operatingSystem);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("mi_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_PUSHOKEN = "SELECT mi_push_token FROM mobile_info WHERE mi_id = ? ;";

	public String getPushToken(final Connection conn, final int mobileInfoId) throws SQLException {

		PreparedStatement psmt = null;
		String pushToken = null;
		try {
			psmt = conn.prepareStatement(SELECT_PUSHOKEN);

			int i = 0;
			psmt.setInt(++i, mobileInfoId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				pushToken = rs.getString("mi_push_token");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return pushToken;
	}

	private final static String SELECT_PUSHTOKEN = "SELECT mi.mi_push_token FROM mobile_info mi LEFT JOIN token_room_detail trd ON mi.mi_id = trd.trd_mobile_info_id "
			+ "WHERE trd.trd_roomno = ?;";

	public List<String> getPushToken(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;

		List<String> list = new ArrayList<String>();

		try {
			psmt = conn.prepareStatement(SELECT_PUSHTOKEN);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("mi_push_token"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE_INFO = "UPDATE mobile_info SET mi_version = ? , mi_push_token = ? WHERE mi_id = ? ;";

	public void update(final Connection conn, final int mobileInfoId, final String version, final String pushToken)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_INFO);

			int i = 0;

			psmt.setString(++i, version);
			psmt.setString(++i, pushToken);
			psmt.setInt(++i, mobileInfoId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_ID_BY_PUSHTOKEN = "SELECT mi_id FROM mobile_info WHERE mi_device = ?; ";

	public int getId(final Connection conn, final String device) throws SQLException {

		PreparedStatement psmt = null;

		int id = -1;

		try {
			psmt = conn.prepareStatement(SELECT_ID_BY_PUSHTOKEN);

			int i = 0;
			psmt.setString(++i, device);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("mi_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

}
