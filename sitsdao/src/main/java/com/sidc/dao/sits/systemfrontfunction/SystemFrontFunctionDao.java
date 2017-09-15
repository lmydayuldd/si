package com.sidc.dao.sits.systemfrontfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemFrontFunctionDao {

	private static final class lazyHolder {
		public static SystemFrontFunctionDao INSTANCE = new SystemFrontFunctionDao();
	}

	public static SystemFrontFunctionDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_FUNCTIONID = "SELECT Function_ID FROM system_front_function WHERE Function_Name = ?";

	public int selectFunctionId(final Connection conn, final String functionName) throws SQLException {
		PreparedStatement psmt = null;

		int id = -1;
		try {
			psmt = conn.prepareStatement(SELECT_BY_FUNCTIONID);

			int i = 0;
			psmt.setString(++i, functionName);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("Function_ID");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

}
