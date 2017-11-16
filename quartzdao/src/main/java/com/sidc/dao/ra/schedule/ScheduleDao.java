/**
 * 
 */
package com.sidc.dao.ra.schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sidc.quartz.api.response.ScheduleDataResponse;
import com.sidc.quartz.api.response.ScheduleStatusResponse;

public class ScheduleDao {

	private ScheduleDao() {
	}

	private static class LazyHolder {
		public static final ScheduleDao INSTANCE = new ScheduleDao();
	}

	public static ScheduleDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECTENABLED = "SELECT s_name FROM schedule WHERE s_name = ? AND s_status = 1";

	public ScheduleStatusResponse isEnabled(Connection conn, final String name) throws SQLException {

		PreparedStatement psmt = null;
		ScheduleStatusResponse entity = null;
		try {
			psmt = conn.prepareStatement(SELECTENABLED);
			int i = 0;
			psmt.setString(++i, name);
			ResultSet rs = psmt.executeQuery();

			boolean isEnabled = false;
			if (rs.next()) {
				isEnabled = true;
			}
			entity = new ScheduleStatusResponse(isEnabled);

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String SELECT = "SELECT s_name, s_group, s_status, s_execution_time, s_description, s_commands "
			+ "FROM schedule WHERE s_name = ?";

	public ScheduleDataResponse select(Connection conn, final String name) throws SQLException {

		PreparedStatement psmt = null;
		ScheduleDataResponse entity = null;
		try {
			psmt = conn.prepareStatement(SELECT);
			int i = 0;
			psmt.setString(++i, name);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new ScheduleDataResponse(rs.getString("s_name"), rs.getString("s_group"), 
						rs.getInt("s_status"), rs.getString("s_execution_time"), rs.getString("s_description"),
						rs.getString("s_commands"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String UPDATE = "UPDATE schedule SET setInfo, s_modify_time = NOW() WHERE s_name = ?";

	public void update(Connection conn, final String setInfo, final String jobname) throws SQLException {

		PreparedStatement psmt = null;
		try {
			final String UPDATE_SCHEDULE = UPDATE.replaceFirst("setInfo", setInfo);
			psmt = conn.prepareStatement(UPDATE_SCHEDULE);

			int i = 0;
			psmt.setString(++i, jobname);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_STATUS = "UPDATE schedule SET s_status = ?, s_modify_time = NOW() WHERE s_name = ?";

	public void updateStatus(Connection conn, final int status, final String name) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(UPDATE_STATUS);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setString(++i, name);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

}
