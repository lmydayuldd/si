package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuDefaultModeResponse;

/**
 * 
 * @author Allen Huang
 *
 */
public class RcuModeDao {

	private RcuModeDao() {
	}

	private static class LazyHolder {
		public static final RcuModeDao INSTANCE = new RcuModeDao();
	}

	public static RcuModeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_MODE = "SELECT R.room_no, M.content FROM rcu_room_mode R LEFT JOIN rcu_mode_agent A "
			+ "ON R.rcu_mode_id = A.rcu_mode_id LEFT JOIN rcu_mode M ON A.rcu_mode_id = M.id "
			+ "WHERE R.room_no = ? AND M.keyname = ?";

	public StringBuilder selectMode(final Connection conn, final String roomno, final String keyname)
			throws SQLException {

		StringBuilder builder = new StringBuilder();
		PreparedStatement psmt = null;
		try {
			int i = 0;
			psmt = conn.prepareStatement(SELECT_MODE);
			psmt.setString(++i, roomno);
			psmt.setString(++i, keyname);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				builder.append(rs.getString("content"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return builder;
	}

	private final static String SELECT_COMMON_MODE = "SELECT content FROM rcu_mode WHERE keyname = ?";

	public StringBuilder selectCommonMode(final Connection conn, final String keyname) throws SQLException {

		StringBuilder builder = new StringBuilder();
		PreparedStatement psmt = null;
		try {
			int i = 0;
			psmt = conn.prepareStatement(SELECT_COMMON_MODE);
			psmt.setString(++i, keyname);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				builder.append(rs.getString("content"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return builder;
	}

	private final static String SEARCH_BY_ID = "SELECT id, keyname, content FROM rcu_mode WHERE id = ?";

	public String searchById(final Connection conn, final int id) throws SQLException {

		String content = null;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BY_ID);
			int i = 0;
			psmt.setInt(++i, id);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				content = rs.getString("content");
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return content;
	}

	private final static String SEARCH_DEFAULT_MODE = "SELECT id, keyname, content, timer FROM rcu_mode WHERE status = 1 ORDER BY id ASC;";

	public List<RcuDefaultModeResponse> searchDefaultMode(final Connection conn) throws SQLException {

		List<RcuDefaultModeResponse> list = new ArrayList<RcuDefaultModeResponse>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_DEFAULT_MODE);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RcuDefaultModeResponse(rs.getInt("id"), rs.getString("keyname"), rs.getString("content"),rs.getInt("timer")));
			}
			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}
}
