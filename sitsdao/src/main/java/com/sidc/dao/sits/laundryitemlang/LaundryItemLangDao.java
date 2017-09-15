package com.sidc.dao.sits.laundryitemlang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;

public class LaundryItemLangDao {

	private static final class lazyHolder {
		public static LaundryItemLangDao INSTANCE = new LaundryItemLangDao();
	}

	public static LaundryItemLangDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_item_lang(lil_laundry_item_id,lil_lang,lil_name,lil_description"
			+ ",lil_creation_time) VALUES(?,?,?,?,NOW());";

	public int insert(final Connection conn, final int itemId, final String langcode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langcode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_item_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_ID_LANGCODE = "SELECT lil_name,lil_description FROM laundry_item_lang WHERE lil_laundry_item_id = ? AND lil_lang = ?;";

	public List<LaundryLangBean> select(final Connection conn, final int itemId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_LANGCODE);
			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(langCode, rs.getString("lil_name"), rs.getString("lil_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT lil_lang,lil_name,lil_description FROM laundry_item_lang "
			+ "WHERE lil_laundry_item_id = ? ;";

	public List<LaundryLangBean> select(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryLangBean> list = new ArrayList<LaundryLangBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);
			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryLangBean(rs.getString("lil_lang"), rs.getString("lil_name"),
						rs.getString("lil_description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM laundry_item_lang WHERE lil_laundry_item_id = ?;";

	public void delete(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, itemId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_NAME = "SELECT lil_name FROM laundry_item_lang WHERE lil_laundry_item_id = ? AND lil_lang = ?;";

	public String selectName(final Connection conn, final int itemId, final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SELECT_NAME);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("lil_name");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

	private final static String SELECT_NAME_TOP1 = "SELECT lil_name FROM laundry_item_lang WHERE lil_laundry_item_id = ?"
			+ " LIMIT 1;";

	/**
	 * 選取db中的任一筆資料
	 * 
	 * @param conn
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public String selectName(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SELECT_NAME_TOP1);

			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("lil_name");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}
}
