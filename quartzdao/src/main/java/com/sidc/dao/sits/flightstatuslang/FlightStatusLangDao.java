package com.sidc.dao.sits.flightstatuslang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightStatusLangDao {

	private FlightStatusLangDao() {
	}

	private static class LazyHolder {
		public static final FlightStatusLangDao INSTANCE = new FlightStatusLangDao();
	}

	public static FlightStatusLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_NAME = "SELECT fsl_name FROM flight_status_lang WHERE "
			+ "fsl_source = ? AND fsl_status = ? AND fal_lang = ? ;";

	public String findName(final Connection conn, final String source, final String status, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SELECT_NAME);

			int i = 0;
			psmt.setString(++i, source);
			psmt.setString(++i, status);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("fsl_name");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

}
