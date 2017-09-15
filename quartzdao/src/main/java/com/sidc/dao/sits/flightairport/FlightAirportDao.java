package com.sidc.dao.sits.flightairport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightAirportDao {

	private FlightAirportDao() {
	}

	private static class LazyHolder {
		public static final FlightAirportDao INSTANCE = new FlightAirportDao();
	}

	public static FlightAirportDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_IATACODE = "SELECT fa_id FROM flight_airport WHERE fa_iata_code = ?;";

	public int selectIdByIataCode(final Connection conn, final String iataCode) throws SQLException {

		PreparedStatement psmt = null;
		int id = -9999;
		try {
			psmt = conn.prepareStatement(SELECT_BY_IATACODE);

			int i = 0;
			psmt.setString(++i, iataCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("fa_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

}
