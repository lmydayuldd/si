package com.sidc.dao.sits.systemfrontmenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemFrontMenuDao {

	private static final class lazyHolder {
		public static SystemFrontMenuDao INSTANCE = new SystemFrontMenuDao();
	}

	public static SystemFrontMenuDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_FUNCTIONID = "SELECT IS_Pay_Service FROM system_front_menu WHERE Function_ID = ?";

	public boolean isPayService(final Connection conn, final int functionId) throws SQLException {
		PreparedStatement psmt = null;

		boolean isPayService = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_FUNCTIONID);

			int i = 0;
			psmt.setInt(++i, functionId);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPayService = rs.getString("IS_Pay_Service").equals("1") ? true : false;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPayService;
	}

}
