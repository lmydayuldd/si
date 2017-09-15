/**
 * 
 */
package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RcuGroupModeDao {

	private static class LazyHolder {
		public static final RcuGroupModeDao INSTANCE = new RcuGroupModeDao();
	}

	public static RcuGroupModeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SEARCH_WITH_RCUGROUP = "SELECT rgm.rcu_mode_id FROM rcu_group_mode rgm LEFT JOIN rcu_mode_agent rma ON rgm.rcu_mode_id = rma.rcu_mode_id WHERE rgm.rcu_group_id = ? AND rma.rcu_agent_behavior_id = ?;";

	/**
	 * 
	 * @param conn
	 * @param groupId
	 * @param behaviorId
	 *            模式 EX check in,check out
	 * @return
	 * @throws SQLException
	 */
	public int searchWithRcuGroup(final Connection conn, final int groupId, final int behaviorId) throws SQLException {

		Integer id = 0;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_WITH_RCUGROUP);
			int i = 0;
			psmt.setInt(++i, groupId);
			psmt.setInt(++i, behaviorId);
			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt("rcu_mode_id");
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_CONTENT = "SELECT content FROM rcu_group_mode WHERE rcu_group_id = ? AND rcu_mode_id = ?;";

	public String findContent(final Connection conn, final int groupId, final int modeId) throws SQLException {

		String content = null;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_CONTENT);

			int i = 0;
			psmt.setInt(++i, groupId);
			psmt.setInt(++i, modeId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				content = rs.getString("content");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return content;
	}

	private final static String INSERT = "INSERT INTO rcu_group_mode(rcu_group_id,rcu_mode_id,content,createdtime)VALUES(?,?,?,NOW());";

	public void insert(final Connection conn, final int groupId, final int modeId, final String content)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setInt(++i, groupId);
			psmt.setInt(++i, modeId);
			psmt.setString(++i, content);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
