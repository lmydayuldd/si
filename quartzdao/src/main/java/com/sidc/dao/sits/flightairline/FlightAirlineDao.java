package com.sidc.dao.sits.flightairline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightAirlineDao {

	private FlightAirlineDao() {
	}

	private static class LazyHolder {
		public static final FlightAirlineDao INSTANCE = new FlightAirlineDao();
	}

	public static FlightAirlineDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_IATACODE = "SELECT fa_id FROM flight_airline WHERE fa_iata_code = ?;";

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

	private final static String SELECT_BY_ICAOCODE = "SELECT fa_id FROM flight_airline WHERE fa_icao_code = ?;";

	public int selectIdByIcaoCode(final Connection conn, final String icaoCode) throws SQLException {

		PreparedStatement psmt = null;
		int id = -9999;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ICAOCODE);

			int i = 0;
			psmt.setString(++i, icaoCode);

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
