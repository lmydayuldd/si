/**
 * 
 */
package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RcuGroupModeDao {

	private static class LazyHolder {
		public static final RcuGroupModeDao INSTANCE = new RcuGroupModeDao();
	}

	public static RcuGroupModeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_CONTENT = "SELECT content FROM rcu_group_mode WHERE rcu_group_id = ? AND rcu_mode_id = ?;";

	public String findContent(final Connection conn, final int groupId, final int modeId) throws SQLException {

		String content = "";

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

	private final static String UPDATE = "UPDATE rcu_group_mode SET content = ? "
			+ "WHERE rcu_group_id = ? AND rcu_mode_id = ?;";

	public void update(final Connection conn, final int groupId, final int modeId, final String content)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setString(++i, content);
			psmt.setInt(++i, groupId);
			psmt.setInt(++i, modeId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM rcu_group_mode WHERE rcu_mode_id = ?;";

	public void delete(final Connection conn, final int modeId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, modeId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_NOT_DEFAULT = "DELETE FROM rcu_group_mode WHERE rcu_mode_id in (SELECT id FROM rcu_mode WHERE status != 1)";

	public void deleteByNotDefault(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_NOT_DEFAULT);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_ALL = "DELETE FROM rcu_group_mode;";

	public void deleteAll(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_ALL);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_GROUPID = "DELETE FROM rcu_group_mode WHERE rcu_group_id = ?;";

	public void deleteByGroupId(final Connection conn, final int groupId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_GROUPID);

			int i = 0;
			psmt.setInt(++i, groupId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT id FROM rcu_group_mode WHERE rcu_group_id = ? AND rcu_mode_id = ?;";

	public boolean isExist(final Connection conn, final int groupId, final int modeId) throws SQLException {

		boolean isExist = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setInt(++i, groupId);
			psmt.setInt(++i, modeId);

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

	private final static String SELECT_MODE_ID = "SELECT rcu_mode_id FROM rcu_group_mode WHERE rcu_group_id = ?;";

	public List<Integer> findModeId(final Connection conn, final int groupId) throws SQLException {

		List<Integer> modeIds = new ArrayList<Integer>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_MODE_ID);

			int i = 0;
			psmt.setInt(++i, groupId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				modeIds.add(rs.getInt("rcu_mode_id"));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return modeIds;
	}
}
