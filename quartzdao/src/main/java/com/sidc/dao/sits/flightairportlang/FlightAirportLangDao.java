package com.sidc.dao.sits.flightairportlang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightAirportLangDao {

	private FlightAirportLangDao() {
	}

	private static class LazyHolder {
		public static final FlightAirportLangDao INSTANCE = new FlightAirportLangDao();
	}

	public static FlightAirportLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_ID_LANGCODE = "SELECT fal_name FROM flight_airport_lang WHERE "
			+ "fal_flight_airport_id = ? AND fal_lang = ? ;";

	public String findName(final Connection conn, final int airportCodeId, final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_LANGCODE);

			int i = 0;
			psmt.setInt(++i, airportCodeId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("fal_name");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

}
