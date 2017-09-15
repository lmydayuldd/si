package com.sidc.dao.sits.shoppingitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.bean.ShopItemBean;
import com.sidc.blackcore.api.sits.shop.response.ShoppingItemResponse;

public class ShoppingItemDao {
	/**
	 * @author Joe
	 */
	private ShoppingItemDao() {
	}

	private static class LazyHolder {
		public static final ShoppingItemDao INSTANCE = new ShoppingItemDao();
	}

	public static ShoppingItemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO shopping_item(si_docno,si_shopping_category_id,si_shopping_vendor_id,si_status,si_sequence"
			+ ",si_price,si_selling_price,si_qty,si_weight,si_modify_time,si_creation_time)VALUES (?,?,?,?,?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final String docno, final int categoryId, final int vendorId,
			final String status, final int sequence, final float price, final float sellingPrice, final int qty,
			final float weight) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setString(++i, docno);
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, vendorId);
			psmt.setString(++i, status);
			psmt.setInt(++i, sequence);
			psmt.setFloat(++i, price);
			psmt.setFloat(++i, sellingPrice);
			psmt.setInt(++i, qty);
			psmt.setFloat(++i, weight);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("shopping_item insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE shopping_item SET si_shopping_category_id = ?,si_shopping_vendor_id = ?,si_status=?,"
			+ "si_sequence=?,si_price=?,si_selling_price=?,si_qty=?,si_weight=?,si_modify_time = NOW() WHERE si_id = ? ; ";

	public void update(final Connection conn, final int id, final int categoryId, final int vendorId,
			final String status, final int sequence, final float price, final float sellingPrice, final int qty,
			final float weight) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, vendorId);
			psmt.setString(++i, status);
			psmt.setInt(++i, sequence);
			psmt.setFloat(++i, price);
			psmt.setFloat(++i, sellingPrice);
			psmt.setFloat(++i, weight);
			psmt.setInt(++i, qty);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_QTY = "UPDATE shopping_item SET si_qty = ?,si_modify_time = NOW() WHERE si_id = ? ;";

	public void updateQty(final Connection conn, final int id, final int qty) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_QTY);

			int i = 0;
			psmt.setInt(++i, qty);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_DOCNO = "UPDATE shopping_item SET si_docno = ?,si_modify_time = NOW() WHERE si_id = ? ;";

	public void updateDocno(final Connection conn, final int id, final String docno) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_DOCNO);

			int i = 0;
			psmt.setString(++i, docno);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_STATUS = "UPDATE shopping_item SET si_status = ?,si_modify_time = NOW() WHERE si_id = ? ;";

	public void updateStatus(final Connection conn, final int id, final String status) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_STATUS);

			int i = 0;
			psmt.setString(++i, status);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT si_id FROM shopping_item WHERE si_id = ?";

	public boolean isExist(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isPass = true;
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String IS_EXIST_BY_STATUS = "SELECT si_id FROM shopping_item WHERE si_id = ? AND si_status = ?";

	public boolean isExist(final Connection conn, final int id, final String status) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST_BY_STATUS);

			int i = 0;
			psmt.setInt(++i, id);
			psmt.setString(++i, status);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isPass = true;
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT_INFO_BY_ID = "SELECT si_shopping_category_id,si_shopping_vendor_id,si_selling_price,si_qty FROM "
			+ "shopping_item WHERE si_id = ?;";

	public ShopItemBean selectItemInfo(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		ShopItemBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_INFO_BY_ID);

			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				entity = new ShopItemBean(itemId, rs.getInt("si_shopping_category_id"),
						rs.getInt("si_shopping_vendor_id"), rs.getFloat("si_selling_price"), rs.getInt("si_qty"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String SELECT = "SELECT si_id,si_docno,si_shopping_category_id,si_shopping_vendor_id,si_status,si_selling_price,"
			+ "si_price,si_qty,si_sequence,si_weight,DATE_FORMAT(si_creation_time,'%Y/%m/%d %H:%i')AS si_creation_time FROM shopping_item ";

	public List<ShoppingItemResponse> select(final Connection conn, final int itemId, final int categoryId,
			final int vendorId, final String status) throws SQLException {

		PreparedStatement psmt = null;
		List<ShoppingItemResponse> list = new ArrayList<ShoppingItemResponse>();
		try {
			String parameter = "";
			int x = 0;
			if (itemId > 0) {
				parameter = formatWhereDescription(x++, " si_id = " + itemId, parameter);
			}
			if (categoryId > 0) {
				parameter = formatWhereDescription(x++, " si_shopping_category_id = " + categoryId, parameter);
			}
			if (!StringUtils.isBlank(status)) {
				parameter = formatWhereDescription(x++, " si_status = '" + status + "'", parameter);
			}
			if (vendorId > 0) {
				parameter = formatWhereDescription(x++, " si_shopping_vendor_id = " + vendorId, parameter);
			}

			if (StringUtils.isBlank(parameter)) {
				psmt = conn.prepareStatement(SELECT);
			} else {
				psmt = conn.prepareStatement(SELECT + parameter);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ShoppingItemResponse(rs.getInt("si_id"), rs.getInt("si_shopping_category_id"),
						rs.getInt("si_shopping_vendor_id"), rs.getString("si_status"), rs.getInt("si_sequence"),
						rs.getFloat("si_price"), rs.getFloat("si_selling_price"), rs.getInt("si_qty"),
						rs.getFloat("si_weight"), rs.getString("si_creation_time")));
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
			description += " WHERE" + sqlWhereDescription;
		} else {
			description += " AND" + sqlWhereDescription;
		}
		return description;
	}

}
