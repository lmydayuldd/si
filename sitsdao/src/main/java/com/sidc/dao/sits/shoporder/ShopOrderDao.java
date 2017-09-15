package com.sidc.dao.sits.shoporder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 
 * @author Allen Huang
 *
 */
public class ShopOrderDao {

	private ShopOrderDao() {
		// TODO Auto-generated constructor stub
	}

	private static class LazyHolder {
		public static final ShopOrderDao INSTANCE = new ShopOrderDao();
	}

	public static ShopOrderDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT_SHOP_ORDER = "INSERT INTO shop_order (order_id, cons_id, amounts, order_time, "
			+ "consume_type) VALUES (?, ?, ?, ?, ?)";

	public void insertShopOrder(final Connection conn, final String orderId, final String consId, final float amounts,
			final short type) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_SHOP_ORDER);

			int i = 0;
			psmt.setString(++i, orderId);
			psmt.setString(++i, consId);
			psmt.setFloat(++i, amounts);
			psmt.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
			psmt.setShort(++i, type);
			psmt.addBatch();
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String INSERT_SHOP_ORDER_DETAIL = "INSERT INTO shop_order_detail (order_id, shop_id, quantity) "
			+ "VALUES (?, ?, ?)";

	public void insertShopOrderDetail(final Connection conn, final String orderId, final int shopId,
			final int quantity) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_SHOP_ORDER_DETAIL);

			int i = 0;
			psmt.setString(++i, orderId);
			psmt.setInt(++i, shopId);
			psmt.setInt(++i, quantity);
			psmt.addBatch();
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
	private final static String FIND_CONS_ID = "SELECT cons_id FROM shop_order WHERE order_id = ?";
	
	public String findConsId(final Connection conn, final String orderId) throws SQLException {
		
		String consId = "";
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(FIND_CONS_ID);

			int i = 0;
			psmt.setString(++i, orderId);
			
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				consId = rs.getString("cons_id");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		
		return consId;
	}
	
	private final static String UPDATE_SHOP_ORDER = "UPDATE shop_order SET amounts = ?, modify_time = ? WHERE order_id = ?";
	
	public void updateShopOrder(final Connection conn, final String orderId, final float amounts) throws SQLException {
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_SHOP_ORDER);

			int i = 0;
			psmt.setFloat(++i, amounts);
			psmt.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
			psmt.setString(++i, orderId);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
	private final static String DELETE_SHOP_ORDER = "DELETE FROM shop_order WHERE order_id = ?";
	
	public void deleteShopOrder(final Connection conn, final String orderId) throws SQLException {
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_SHOP_ORDER);

			int i = 0;
			psmt.setString(++i, orderId);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
	private final static String DELETE_SHOP_ORDER_DETAIL = "DELETE FROM shop_order_detail WHERE order_id = ?";
	
	public void deleteShopOrderDetail(final Connection conn, final String orderId) throws SQLException {
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_SHOP_ORDER_DETAIL);

			int i = 0;
			psmt.setString(++i, orderId);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
