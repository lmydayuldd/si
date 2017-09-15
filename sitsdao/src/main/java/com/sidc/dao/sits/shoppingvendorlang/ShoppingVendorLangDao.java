package com.sidc.dao.sits.shoppingvendorlang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;

public class ShoppingVendorLangDao {
	/**
	 * @author Joe
	 */
	private ShoppingVendorLangDao() {
	}

	private static class LazyHolder {
		public static final ShoppingVendorLangDao INSTANCE = new ShoppingVendorLangDao();
	}

	public static ShoppingVendorLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO shopping_vendor_lang(svl_shopping_vendor_id,svl_lang,svl_name,svl_description,"
			+ "svl_modify_time,svl_creation_time) VALUES (?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int vendorId, final String langCode, final String name,
			final String description) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, vendorId);
			psmt.setString(++i, langCode);
			psmt.setString(++i, name);
			psmt.setString(++i, description);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("shopping_vendor_lang insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String DELETE = "DELETE FROM shopping_vendor_lang WHERE svl_shopping_vendor_id = ?;";

	public void delete(final Connection conn, final int vendorId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, vendorId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_PARAMETER = "SELECT svl_lang,svl_name,svl_description FROM shopping_vendor_lang WHERE"
			+ " svl_shopping_vendor_id = ? ";

	public List<ShopLangBean> select(final Connection conn, final int vendorId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ShopLangBean> list = new ArrayList<ShopLangBean>();
		try {

			String description = "";
			if (!StringUtils.isBlank(langCode)) {
				description = " AND svl_lang = '" + langCode + "'";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			int i = 0;
			psmt.setInt(++i, vendorId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ShopLangBean(rs.getString("svl_name"), rs.getString("svl_lang"),
						rs.getString("svl_description")));
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
