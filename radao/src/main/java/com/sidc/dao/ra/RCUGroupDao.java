/**
 * 
 */
package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Nandin
 *
 */
public class RCUGroupDao {

	private RCUGroupDao() {
	}

	private static class LazyHolder {
		public static final RCUGroupDao INSTANCE = new RCUGroupDao();
	}

	public static RCUGroupDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT IGNORE INTO rcu_group(id, name, createdtime) "
			+ " VALUES(?, ?, now())";

	public void insert(Connection conn, int id, String group) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, id);
			psmt.setString(++i, group);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String UPDATE = "UPDATE rcu_group SET name = ? WHERE id = ?";

	public void update(Connection conn, int id, String group) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setString(++i, group);
			psmt.setInt(++i, id);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String DELETE_ALL = "DELETE FROM rcu_group";

	public void delete(Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_ALL);
			psmt.executeUpdate();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String LIST_LANG = "SELECT id FROM rcu_group ORDER BY id DESC LIMIT 1;";

	public int findId(final Connection conn) throws SQLException {

		int id = -9999;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_LANG);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			} else {
				id = 0;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String IS_EXIT = "SELECT id FROM rcu_group WHERE id = ?;";

	public boolean isExist(final Connection conn, final int id) throws SQLException {

		boolean isExist = false;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_EXIT);

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

	private final static String DELETE = "DELETE FROM rcu_group WHERE id = ?;";

	public void delete(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, id);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
