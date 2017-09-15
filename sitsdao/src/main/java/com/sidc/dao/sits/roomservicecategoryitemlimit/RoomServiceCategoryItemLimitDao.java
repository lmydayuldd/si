package com.sidc.dao.sits.roomservicecategoryitemlimit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomServiceCategoryItemLimitDao {
	/**
	 * @author Joe
	 */
	private RoomServiceCategoryItemLimitDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceCategoryItemLimitDao INSTANCE = new RoomServiceCategoryItemLimitDao();
	}

	public static RoomServiceCategoryItemLimitDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_category_item_limit(rcil_roomservice_category_id,rcil_roomservice_item_id,"
			+ "rcil_limit_qty,rcil_creation_time) VALUES (?,?,?,NOW())";

	public int insert(final Connection conn, final int categoryId, final int itemId, final int qty)
			throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, qty);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("roomservice_category_item_limit insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String DELETE = "DELETE FROM roomservice_category_item_limit WHERE rcil_roomservice_item_id = ?";

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

}
