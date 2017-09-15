package com.sidc.dao.sits.spareorderline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.spare.bean.SpareOrderLineAmountBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareOrderLineBean;

public class SpareOrderLineDao {
	/**
	 * @author Joe
	 */
	private SpareOrderLineDao() {
	}

	private static class LazyHolder {
		public static final SpareOrderLineDao INSTANCE = new SpareOrderLineDao();
	}

	public static SpareOrderLineDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO spare_order_line(sol_spare_order_header_id,sol_spare_category_id,"
			+ "sol_spare_item_id,sol_amount,sol_qty,sol_modify_time,sol_creation_time) "
			+ "VALUES (?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int orderHeaderId, final int categoryId, final int itemId,
			final float amount, final int qty) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, orderHeaderId);
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, itemId);
			psmt.setFloat(++i, amount);
			psmt.setInt(++i, qty);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("spare_order_line insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_AMOUNT = "SELECT sol_spare_item_id,sol_amount,sol_qty FROM spare_order_line WHERE "
			+ "sol_spare_order_header_id = ?;";

	public List<SpareOrderLineAmountBean> selectAmount(final Connection conn, final int orderId) throws SQLException {

		PreparedStatement psmt = null;
		List<SpareOrderLineAmountBean> list = new ArrayList<SpareOrderLineAmountBean>();
		try {
			psmt = conn.prepareStatement(SELECT_AMOUNT);

			int i = 0;
			psmt.setInt(++i, orderId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new SpareOrderLineAmountBean(rs.getInt("sol_spare_item_id"), rs.getFloat("sol_amount"),
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

	private final static String SELECT = "SELECT sol_spare_category_id,sol_spare_item_id,sol_amount,sol_qty,"
			+ "(SELECT sil_name FROM spare_item_lang WHERE sil_lang = ? AND sil_spare_item_id = sol_spare_item_id ) AS item_name,"
			+ "(SELECT scl_name FROM spare_category_lang WHERE scl_lang = ? AND scl_spare_category_id = sol_spare_category_id ) AS "
			+ "category_name FROM spare_order_line WHERE sol_spare_order_header_id = ?;";

	public List<SpareOrderLineBean> select(final Connection conn, final int orderId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<SpareOrderLineBean> list = new ArrayList<SpareOrderLineBean>();
		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setString(++i, langCode);
			psmt.setInt(++i, orderId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new SpareOrderLineBean(rs.getInt("sol_spare_category_id"), rs.getString("category_name"),
						rs.getInt("sol_spare_item_id"), rs.getString("item_name"), rs.getFloat("sol_amount"),
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

	private final static String SELECT_BY_PARAMETER = "SELECT DISTINCT sol_spare_order_header_id FROM spare_order_line ";
	private final String PARAMETER_CATEGORY_ID = " sol_spare_category_id IN ";
	private final String PARAMETER_ITEM_ID = " sol_spare_item_id IN ";

	public List<Integer> selectOrderIdByWhereIn(final Connection conn, final String categorySqlInStr,
			final String itemSqlInStr) throws SQLException {

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

			if (description.equals("WHERE")) {
				description = "";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("sol_spare_order_header_id"));
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
