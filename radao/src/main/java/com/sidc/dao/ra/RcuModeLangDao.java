package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RcuModeLangDao {

	private RcuModeLangDao() {
	}

	private static class LazyHolder {
		public static final RcuModeLangDao INSTANCE = new RcuModeLangDao();
	}

	public static RcuModeLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String DELETE_IS_NOT_DEFAULT = "DELETE FROM rcu_mode_lang WHERE rcu_mode_id IN "
			+ "(SELECT id FROM rcu_mode WHERE status != 1);";

	public void deleteByNotDefualt(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_IS_NOT_DEFAULT);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_ALL = "DELETE FROM rcu_mode_lang;";

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

	private final static String INSERT = "INSERT INTO rcu_mode_lang(rcu_mode_id,lang,name,description)VALUES(?,?,?,?);";

	public void insert(final Connection conn, final int modeId, final String lang, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);
			int i = 0;
			psmt.setInt(++i, modeId);
			psmt.setString(++i, lang);
			psmt.setString(++i, name);
			psmt.setString(++i, description);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
	private final static String DELETE_BY_MODEID = "DELETE FROM rcu_mode_lang WHERE rcu_mode_id = ?";

	public void delete(Connection conn, final int modeId) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE_BY_MODEID);

			int i = 0;
			psmt.setInt(++i, modeId);

			psmt.executeUpdate();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

}
