package com.sidc.dao.sits.roletofunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleToFuntionDao {
	/**
	 * @author Joe
	 */
	private RoleToFuntionDao() {
	}

	private static class LazyHolder {
		public static final RoleToFuntionDao INSTANCE = new RoleToFuntionDao();
	}

	public static RoleToFuntionDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String IS_EXIST_BY_ROLEID = "SELECT role_id FROM role_to_function WHERE function_id = ? AND role_id = ? ";

	public boolean isExist(final Connection conn, final String functionId, final String roleId) throws SQLException {
		boolean isExist = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_EXIST_BY_ROLEID);

			int i = 0;
			psmt.setString(++i, functionId);
			psmt.setString(++i, roleId);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isExist = true;
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}

	private final static String SELECT_BY_ROLEID = "SELECT function_id FROM role_to_function WHERE role_id = ? ";

	public List<String> listFunctionId(final Connection conn, final String roleId) throws SQLException {

		PreparedStatement psmt = null;
		List<String> list = new ArrayList<String>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ROLEID);

			int i = 0;
			psmt.setString(++i, roleId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("function_id"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
