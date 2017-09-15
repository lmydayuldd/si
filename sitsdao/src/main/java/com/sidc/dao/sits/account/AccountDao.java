/**
 * 
 */
package com.sidc.dao.sits.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Nandin
 *
 */
public class AccountDao {

	private AccountDao() {
	}

	private static class LazyHolder {
		public static final AccountDao INSTANCE = new AccountDao();
	}

	public static AccountDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String LOGIN = "SELECT user_id FROM system_user WHERE user_id = ? and password=?";

	public boolean login(final Connection conn, final String user, final String password) throws SQLException {

		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LOGIN);

			int i = 0;
			psmt.setString(++i, user);
			psmt.setString(++i, password);

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

	private final static String INSERT = "INSERT INTO system_user(user_id,role_id,display_name,email,password)"
			+ "VALUES(?,?,?,?,?);";

	public void insert(final Connection conn, final String id, final String roleId, final String name,
			final String email, final String password) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, id);
			psmt.setString(++i, roleId);
			psmt.setString(++i, name);
			psmt.setString(++i, email);
			psmt.setString(++i, password);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}
	
	private final static String IS_EXIST = "SELECT user_id FROM system_user WHERE user_id = ? ;";

	public boolean isExist(final Connection conn, final String id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setString(++i, id);

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

}
