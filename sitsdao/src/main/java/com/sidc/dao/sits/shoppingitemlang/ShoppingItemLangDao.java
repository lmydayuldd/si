package com.sidc.dao.sits.shoppingitemlang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingItemLangDao {
	/**
	 * @author Joe
	 */
	private ShoppingItemLangDao() {
	}

	private static class LazyHolder {
		public static final ShoppingItemLangDao INSTANCE = new ShoppingItemLangDao();
	}

	public static ShoppingItemLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO shopping_item_lang(sil_shopping_item_id,sil_lang,sil_name,sil_description,"
			+ "sil_introduction,sil_modify_time,sil_creation_time) VALUES (?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int itemId, final String langCode, final String name,
			final String description, final String introduction) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langCode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.setString(++i, introduction);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("shopping_item_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String DELETE = "DELETE FROM shopping_item_lang WHERE sil_shopping_item_id = ?;";

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

	private final static String SELECT_BY_PARAMETER = "SELECT sil_lang,sil_name,sil_description FROM shopping_item_lang WHERE"
			+ " sil_shopping_item_id = ? ";

	public List<ShopLangBean> select(final Connection conn, final int categoryId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ShopLangBean> list = new ArrayList<ShopLangBean>();
		try {

			String description = "";
			if (!StringUtils.isBlank(langCode)) {
				description = " AND sil_lang = '" + langCode + "'";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			int i = 0;
			psmt.setInt(++i, categoryId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ShopLangBean(rs.getString("sil_name"), rs.getString("sil_lang"),
						rs.getString("sil_description")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_NAME = "SELECT sil_name FROM shopping_item_lang WHERE sil_shopping_item_id = ? AND sil_lang = ?;";

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
				name = rs.getString("sil_name");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

	private final static String SELECT_NAME_TOP1 = "SELECT sil_name FROM shopping_item_lang WHERE sil_shopping_item_id = ?"
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
				name = rs.getString("sil_name");
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
