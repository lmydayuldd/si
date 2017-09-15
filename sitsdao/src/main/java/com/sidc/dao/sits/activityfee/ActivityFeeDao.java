package com.sidc.dao.sits.activityfee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActivityFeeDao {

	private static final class lazyHolder {
		public static ActivityFeeDao INSTANCE = new ActivityFeeDao();
	}

	public static ActivityFeeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_fee(af_status,af_creation_time) VALUES(?,now()); ";

	public int insert(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, status);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_fee insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT af_id FROM activity_fee;";

	public List<Integer> select(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			psmt = conn.prepareStatement(SELECT);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("af_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT af_id FROM activity_fee WHERE af_id = ?;";

	public boolean isExist(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isExist = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}

	private final static String SELECT_BY_STATUS = "SELECT af_id FROM activity_fee WHERE af_status = ?;";

	public List<Integer> selectByStatus(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_STATUS);

			int i = 0;
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("af_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE = "UPDATE activity_fee SET af_status = ? WHERE af_id = ? ; ";

	public void update(final Connection conn, final int afId, final int status) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, afId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
