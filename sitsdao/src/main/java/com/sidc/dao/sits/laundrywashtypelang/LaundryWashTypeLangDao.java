package com.sidc.dao.sits.laundrywashtypelang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryWashTypeLangDao {

	private static final class lazyHolder {
		public static LaundryWashTypeLangDao INSTANCE = new LaundryWashTypeLangDao();
	}

	public static LaundryWashTypeLangDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_wash_type_lang(lwtl_laundry_wash_type_id,lwtl_lang,lwtl_name,lwtl_description"
			+ ",lwtl_creation_time) VALUES(?,?,?,?,NOW());";

	public int insert(final Connection conn, final int washTypeId, final String langcode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, washTypeId);
			psmt.setString(++i, langcode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_wash_type_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_ID_LANGCODE = "SELECT lwtl_name,lwtl_description FROM laundry_wash_type_lang WHERE "
			+ "lwtl_laundry_wash_type_id = ? AND lwtl_lang = ?;";

	public List<LaundryLangBean> select(final Connection conn, final int washTypeId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_LANGCODE);
			int i = 0;
			psmt.setInt(++i, washTypeId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(langCode, rs.getString("lwtl_name"), rs.getString("lwtl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT lwtl_lang,lwtl_name,lwtl_description FROM laundry_wash_type_lang "
			+ "WHERE lwtl_laundry_wash_type_id = ? ;";

	public List<LaundryLangBean> select(final Connection conn, final int washTypeId) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);
			int i = 0;
			psmt.setInt(++i, washTypeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(rs.getString("lwtl_lang"), rs.getString("lwtl_name"),
						rs.getString("lwtl_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM laundry_wash_type_lang WHERE lwtl_laundry_wash_type_id = ?;";

	public void delete(final Connection conn, final int washTypeId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, washTypeId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
