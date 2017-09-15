package com.sidc.dao.sits.sparecategorylang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;

public class SpareCategoryLangDao {
	/**
	 * @author Joe
	 */
	private SpareCategoryLangDao() {
	}

	private static class LazyHolder {
		public static final SpareCategoryLangDao INSTANCE = new SpareCategoryLangDao();
	}

	public static SpareCategoryLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO spare_category_lang(scl_spare_category_id,scl_lang,scl_name,scl_description,"
			+ "scl_modify_time,scl_creation_time) VALUES (?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int categoryId, final String langCode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setString(++i, langCode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("spare_category_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String DELETE = "DELETE FROM spare_category_lang WHERE scl_spare_category_id = ?;";

	public void delete(final Connection conn, final int categoryId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, categoryId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_PARAMETER = "SELECT scl_lang,scl_name,scl_description FROM spare_category_lang WHERE"
			+ " scl_spare_category_id = ? ";

	public List<SpareLangBean> select(final Connection conn, final int categoryId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<SpareLangBean> list = new ArrayList<SpareLangBean>();
		try {

			String description = "";
			if (!StringUtils.isBlank(langCode)) {
				description = " AND scl_lang = '" + langCode + "'";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			int i = 0;
			psmt.setInt(++i, categoryId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new SpareLangBean(rs.getString("scl_name"), rs.getString("scl_lang"),
						rs.getString("scl_description")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
