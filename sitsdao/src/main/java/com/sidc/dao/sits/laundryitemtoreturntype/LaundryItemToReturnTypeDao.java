package com.sidc.dao.sits.laundryitemtoreturntype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaundryItemToReturnTypeDao {

	private static final class lazyHolder {
		public static LaundryItemToReturnTypeDao INSTANCE = new LaundryItemToReturnTypeDao();
	}

	public static LaundryItemToReturnTypeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_item_to_returntype(litr_item_id,litr_return_type_id) VALUES(?,?);";

	public int insert(final Connection conn, final int itemId, final int returnTypeId) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, returnTypeId);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_item_to_returntype insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_BY_ITEMID = "SELECT litr_return_type_id FROM laundry_item_to_returntype LEFT JOIN laundry_return_type ON "
			+ "litr_return_type_id = lry_id WHERE litr_item_id = ? AND lry_status = 1;";

	public List<Integer> selectReturnType(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ITEMID);
			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("litr_return_type_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM laundry_item_to_returntype WHERE litr_item_id = ?;";

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

	private final static String SELECT_BY_ITEMID_RETURNTYPEID = "SELECT litr_item_id FROM laundry_item_to_returntype WHERE litr_item_id = ? "
			+ "AND litr_return_type_id = ?;";

	public boolean isExist(final Connection conn, final int itemId, final int returnTypeId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ITEMID_RETURNTYPEID);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, returnTypeId);

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
