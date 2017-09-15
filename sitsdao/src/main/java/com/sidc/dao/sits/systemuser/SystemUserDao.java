package com.sidc.dao.sits.systemuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemUserDao {

	private static final class lazyHolder {
		public static SystemUserDao INSTANCE = new SystemUserDao();
	}

	public static SystemUserDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_ROLEID = "SELECT role_id FROM system_user WHERE user_id = ?";

	public String getRoleId(final Connection conn, final String userId) throws SQLException {
		PreparedStatement psmt = null;

		String roleId = null;
		try {
			psmt = conn.prepareStatement(SELECT_ROLEID);

			int i = 0;
			psmt.setString(++i, userId);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				roleId = rs.getString("role_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return roleId;
	}

}
