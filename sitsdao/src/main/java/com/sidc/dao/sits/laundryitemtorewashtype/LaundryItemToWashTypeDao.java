package com.sidc.dao.sits.laundryitemtorewashtype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryWashTypeBean;

public class LaundryItemToWashTypeDao {

	private static final class lazyHolder {
		public static LaundryItemToWashTypeDao INSTANCE = new LaundryItemToWashTypeDao();
	}

	public static LaundryItemToWashTypeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_item_to_washtype(litw_item_id,litw_wash_type_id,litw_price) "
			+ "VALUES(?,?,?);";

	public int insert(final Connection conn, final int itemId, final int washTypeId, final int price)
			throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, washTypeId);
			psmt.setInt(++i, price);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_item_to_washtype insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_ITEMID = "SELECT litw_wash_type_id,litw_price FROM laundry_item_to_washtype LFET JOIN laundry_wash_type ON "
			+ "litw_wash_type_id = lwt_id WHERE litw_item_id = ? AND lwt_status = 1;";

	public List<LaundryWashTypeBean> selectWashType(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryWashTypeBean> list = new ArrayList<LaundryWashTypeBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ITEMID);
			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryWashTypeBean(rs.getInt("litw_wash_type_id"), rs.getInt("litw_price")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM laundry_item_to_washtype WHERE litw_item_id = ?;";

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

	private final static String SELECT_PRICE = "SELECT litw_price FROM laundry_item_to_washtype LFET JOIN laundry_wash_type ON "
			+ "litw_wash_type_id = lwt_id WHERE litw_item_id = ? AND litw_wash_type_id = ? AND lwt_status = 1;";

	public float selectPrice(final Connection conn, final int itemId, final int washTypeId) throws SQLException {

		PreparedStatement psmt = null;
		float price = -1;
		try {
			psmt = conn.prepareStatement(SELECT_PRICE);
			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, washTypeId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				price = rs.getFloat("litw_price");
			} else {
				throw new SQLException("select laundry_item_to_washtype fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return price;
	}

	private final static String SELECT_BY_ITEMID_WASHTYPEID = "SELECT litw_item_id FROM laundry_item_to_washtype WHERE litw_item_id = ? AND litw_wash_type_id = ?;";

	public boolean isExist(final Connection conn, final int itemId, final int washTypeId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ITEMID_WASHTYPEID);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, washTypeId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isExist = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}

}
