package com.sidc.dao.sits.shopppingorderline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.bean.ShopOrderLineAmountBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopOrderLineBean;

public class ShoppingOrderLineDao {
	/**
	 * @author Joe
	 */
	private ShoppingOrderLineDao() {
	}

	private static class LazyHolder {
		public static final ShoppingOrderLineDao INSTANCE = new ShoppingOrderLineDao();
	}

	public static ShoppingOrderLineDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO shopping_order_line(sol_shopping_order_header_id,sol_shopping_category_id,"
			+ "sol_shopping_vendor_id,sol_shopping_item_id,sol_amount,sol_qty,sol_modify_time,sol_creation_time) "
			+ "VALUES (?,?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int orderHeaderId, final int categoryId, final int vendorId,
			final int itemId, final float amount, final int qty) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, orderHeaderId);
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, vendorId);
			psmt.setInt(++i, itemId);
			psmt.setFloat(++i, amount);
			psmt.setInt(++i, qty);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("shopping_order_line insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_AMOUNT = "SELECT sol_shopping_item_id,sol_amount,sol_qty FROM shopping_order_line WHERE "
			+ "sol_shopping_order_header_id = ?;";

	public List<ShopOrderLineAmountBean> selectAmount(final Connection conn, final int orderId) throws SQLException {

		PreparedStatement psmt = null;
		List<ShopOrderLineAmountBean> list = new ArrayList<ShopOrderLineAmountBean>();
		try {
			psmt = conn.prepareStatement(SELECT_AMOUNT);

			int i = 0;
			psmt.setInt(++i, orderId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ShopOrderLineAmountBean(rs.getInt("sol_shopping_item_id"), rs.getFloat("sol_amount"),
						rs.getInt("sol_qty")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT = "SELECT sol_shopping_category_id,sol_shopping_vendor_id,"
			+ "sol_shopping_item_id,sol_amount,sol_qty,"
			+ "(SELECT sil_name FROM shopping_item_lang WHERE sil_lang = ? AND sil_shopping_item_id = sol_shopping_item_id ) AS item_name,"
			+ "(SELECT scl_name FROM shopping_category_lang WHERE scl_lang = ? AND scl_shopping_category_id = sol_shopping_category_id ) AS "
			+ "category_name,(SELECT svl_name FROM shopping_vendor_lang WHERE svl_lang = ? AND sol_shopping_vendor_id = "
			+ "svl_shopping_vendor_id ) AS vendor_name FROM shopping_order_line WHERE sol_shopping_order_header_id = ?;";

	public List<ShopOrderLineBean> select(final Connection conn, final int orderId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ShopOrderLineBean> list = new ArrayList<ShopOrderLineBean>();
		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setString(++i, langCode);
			psmt.setString(++i, langCode);
			psmt.setInt(++i, orderId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ShopOrderLineBean(rs.getInt("sol_shopping_category_id"), rs.getString("category_name"),
						rs.getInt("sol_shopping_vendor_id"), rs.getString("vendor_name"),
						rs.getInt("sol_shopping_item_id"), rs.getString("item_name"), rs.getFloat("sol_amount"),
						rs.getInt("sol_qty")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_PARAMETER = "SELECT DISTINCT sol_shopping_order_header_id FROM shopping_order_line ";
	private final String PARAMETER_CATEGORY_ID = " sol_shopping_category_id IN ";
	private final String PARAMETER_ITEM_ID = " sol_shopping_item_id IN ";
	private final String PARAMETER_VENDOR_ID = " sol_shopping_vendor_id IN ";

	public List<Integer> selectOrderIdByWhereIn(final Connection conn, final String categorySqlInStr,
			final String itemSqlInStr, final String vendorSqlInStr) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			String description = "WHERE";
			int x = 0;

			/**
			 * 不用 setArray 因為 sits MySQL版本太舊... 會出現
			 * SQLFeatureNotSupportedException, MySQL version 5.0 ....
			 * 2017/08/07
			 */

			if (!StringUtils.isBlank(categorySqlInStr)) {
				description = formatWhereDescription(x++, PARAMETER_CATEGORY_ID + "(" + categorySqlInStr + ")",
						description);
			}

			if (!StringUtils.isBlank(itemSqlInStr)) {
				description = formatWhereDescription(x++, PARAMETER_ITEM_ID + "(" + itemSqlInStr + ")", description);
			}
			if (!StringUtils.isBlank(vendorSqlInStr)) {
				description = formatWhereDescription(x++, PARAMETER_VENDOR_ID + "(" + vendorSqlInStr + ")",
						description);
			}

			if (description.equals("WHERE")) {
				description = "";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("sol_shopping_order_header_id"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private String formatWhereDescription(int i, final String sqlWhereDescription, String description) {
		if (i == 0) {
			description += sqlWhereDescription;
		} else {
			description += "AND" + sqlWhereDescription;
		}
		return description;
	}

}
