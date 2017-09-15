package com.sidc.dao.sits.activityrepeat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatInfoBean;

public class ActivityRepeatDao {

	private static final class lazyHolder {
		public static ActivityRepeatDao INSTANCE = new ActivityRepeatDao();
	}

	public static ActivityRepeatDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_repeat(ar_activity_id,ar_start_time,ar_end_time,ar_description) "
			+ "VALUES(?,?,?,?);";

	public int insert(final Connection conn, final int activityId, final String starTime, final String endTime,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setString(++i, starTime);
			psmt.setString(++i, endTime);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_repeat insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_BY_ACTIVITYID = "SELECT ar_id,"
			+ "DATE_FORMAT(ar_start_time,'%Y/%m/%d %H:%i')AS ar_start_time,"
			+ "DATE_FORMAT(ar_end_time,'%Y/%m/%d %H:%i')AS ar_end_time," + "ar_description FROM activity_repeat WHERE "
			+ "ar_activity_id = ?;";

	public List<ActivityRepeatInfoBean> selectByActivityId(final Connection conn, final int activityId)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityRepeatInfoBean> list = new ArrayList<ActivityRepeatInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ACTIVITYID);

			int i = 0;
			psmt.setInt(++i, activityId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityRepeatInfoBean(rs.getInt("ar_id"), rs.getString("ar_start_time"),
						rs.getString("ar_end_time"), rs.getString("ar_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM activity_repeat WHERE ar_activity_id=?;";

	public void delete(final Connection conn, final int activityId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, activityId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ACTIVITYID_ID = "SELECT ar_id FROM activity_repeat WHERE ar_activity_id = ? AND ar_id = ?;";

	public boolean isExist(final Connection conn, final int activityId, final int repeatId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ACTIVITYID_ID);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, repeatId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT_STARTTIME = "SELECT DATE_FORMAT(ar_start_time,'%Y/%m/%d %H:%i')AS ar_start_time "
			+ "FROM activity_repeat WHERE ar_activity_id = ? AND ar_id = ?;";

	public String selectStartTime(final Connection conn, final int activityId, final int repeatId) throws SQLException {

		PreparedStatement psmt = null;
		String startTime = null;
		try {
			psmt = conn.prepareStatement(SELECT_STARTTIME);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, repeatId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				startTime = rs.getString("ar_start_time");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return startTime;
	}
}
