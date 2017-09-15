package com.sidc.dao.sits.flightschedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FlightScheduleDao {

	private FlightScheduleDao() {
	}

	private static class LazyHolder {
		public static final FlightScheduleDao INSTANCE = new FlightScheduleDao();
	}

	public static FlightScheduleDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String DELETE_BY_REGION = "DELETE FROM flight_schedule WHERE region = ? ;";

	public void deleteByRegion(final Connection conn, final String region) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE_BY_REGION);

			int i = 0;
			psmt.setString(++i, region);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_WITH_REGION_IS_NULL = "DELETE FROM flight_schedule WHERE region IS NULL;";

	public void deleteWithRegionIsNull(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE_WITH_REGION_IS_NULL);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String INSERT = "INSERT INTO roomservice_category_lang(id,scheduled_time,expected_time,airline,airline_code,flight_no"
			+ ",origin,terminal,gate,plane_type,status,lang_code,region) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public void insert(final Connection conn, final String scheduledTime, final String expectedTime,
			final String airline, final String airlineCode, final String flightNo, final String origin,
			final String terminal, final String gate, final String planeType, final String status,
			final String langCode, final String regrion) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, "id");
			psmt.setString(++i, scheduledTime);
			psmt.setString(++i, expectedTime);
			psmt.setString(++i, airline);
			psmt.setString(++i, airlineCode);
			psmt.setString(++i, flightNo);
			psmt.setString(++i, origin);
			psmt.setString(++i, terminal);
			psmt.setString(++i, gate);
			psmt.setString(++i, planeType);
			psmt.setString(++i, status);
			psmt.setString(++i, langCode);
			psmt.setString(++i, regrion);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

}
