package com.sidc.dao.sits.laundryclasslang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryClassLangDao {

	private static final class lazyHolder {
		public static LaundryClassLangDao INSTANCE = new LaundryClassLangDao();
	}

	public static LaundryClassLangDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_class_lang(lcl_laundry_class_id,lcl_lang,lcl_name,lcl_description"
			+ ",lcl_creation_time) VALUES(?,?,?,?,NOW());";

	public int insert(final Connection conn, final int classId, final String langcode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, classId);
			psmt.setString(++i, langcode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_class_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_ID_LANGCODE = "SELECT lcl_name,lcl_description FROM laundry_class_lang WHERE lcl_laundry_class_id = ? AND lcl_lang = ?;";

	public List<LaundryLangBean> select(final Connection conn, final int classId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_LANGCODE);
			int i = 0;
			psmt.setInt(++i, classId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(langCode, rs.getString("lcl_name"), rs.getString("lcl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT lcl_lang,lcl_name,lcl_description FROM laundry_class_lang "
			+ "WHERE lcl_laundry_class_id = ? ;";

	public List<LaundryLangBean> select(final Connection conn, final int classId) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);
			int i = 0;
			psmt.setInt(++i, classId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(rs.getString("lcl_lang"), rs.getString("lcl_name"),
						rs.getString("lcl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM laundry_class_lang WHERE lcl_laundry_class_id = ?;";

	public void delete(final Connection conn, final int classId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, classId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
