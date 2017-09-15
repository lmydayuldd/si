package com.sidc.dao.sits.laundryreturntypelang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryReturnTypeLangDao {

	private static final class lazyHolder {
		public static LaundryReturnTypeLangDao INSTANCE = new LaundryReturnTypeLangDao();
	}

	public static LaundryReturnTypeLangDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_return_type_lang(lrtl_return_type_id,lrtl_lang,lrtl_name,lrtl_description"
			+ ",lrtl_creation_time) VALUES(?,?,?,?,NOW());";

	public int insert(final Connection conn, final int returnTypeId, final String langcode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, returnTypeId);
			psmt.setString(++i, langcode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_return_type_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_ID_LANGCODE = "SELECT lrtl_name,lrtl_description FROM laundry_return_type_lang WHERE lrtl_return_type_id = ? "
			+ "AND lrtl_lang = ?;";

	public List<LaundryLangBean> select(final Connection conn, final int typeId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_LANGCODE);
			int i = 0;
			psmt.setInt(++i, typeId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(langCode, rs.getString("lrtl_name"), rs.getString("lrtl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT lrtl_lang,lrtl_name,lrtl_description FROM laundry_return_type_lang "
			+ "WHERE lrtl_return_type_id = ? ;";

	public List<LaundryLangBean> select(final Connection conn, final int returnTypeId) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);
			int i = 0;
			psmt.setInt(++i, returnTypeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(rs.getString("lrtl_lang"), rs.getString("lrtl_name"),
						rs.getString("lrtl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM laundry_return_type_lang WHERE lrtl_return_type_id = ?;";

	public void delete(final Connection conn, final int returnTypeId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, returnTypeId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
