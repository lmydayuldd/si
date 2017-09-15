package com.sidc.dao.sits.roomserviceorderline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineAmountBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineInfoBean;

public class RoomServiceOrderLineDao {
	/**
	 * @author Joe
	 */
	private RoomServiceOrderLineDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceOrderLineDao INSTANCE = new RoomServiceOrderLineDao();
	}

	public static RoomServiceOrderLineDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_order_line (rol_roomservice_order_header_id,rol_roomservice_item_id,"
			+ "rol_roomservice_category_id,rol_amount,rol_qty,rol_modify_time,rol_creation_time) VALUES (?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int orderHeaderId, final int itemId, final int categoryId,
			final float amount, final int qty) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, orderHeaderId);
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, categoryId);
			psmt.setFloat(++i, amount);
			psmt.setInt(++i, qty);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("roomservice_order_line insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_INFO = "SELECT DISTINCT rol_roomservice_item_id,rol_id,rol_roomservice_category_id,rol_amount,"
			+ "rol_qty,(SELECT rcl_name FROM roomservice_category_lang WHERE rcl_lang = ? AND rcl_roomservice_category_id = "
			+ "rol_roomservice_category_id LIMIT 1)AS category_name,(SELECT ril_name FROM roomservice_item_lang WHERE ril_lang = ? AND "
			+ "rol_roomservice_item_id = ril_rommservice_item_id LIMIT 1)AS item_name FROM roomservice_order_line WHERE "
			+ "rol_roomservice_order_header_id = ? AND rol_refer_id = ?;";

	public List<RoomServiceOrderLineInfoBean> selectInfo(final Connection conn, final int orderId, final int referId,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceOrderLineInfoBean> list = new ArrayList<RoomServiceOrderLineInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_INFO);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setString(++i, langCode);
			psmt.setInt(++i, orderId);
			psmt.setInt(++i, referId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RoomServiceOrderLineInfoBean(rs.getInt("rol_id"), rs.getInt("rol_roomservice_category_id"),
						rs.getString("category_name"), rs.getInt("rol_roomservice_item_id"), rs.getString("item_name"),
						rs.getFloat("rol_amount"), rs.getInt("rol_qty")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String INSERT_WITH_SET = "INSERT INTO roomservice_order_line (rol_roomservice_order_header_id,rol_roomservice_item_id,"
			+ "rol_roomservice_category_id,rol_refer_id,rol_amount,rol_qty,rol_modify_time,rol_creation_time) VALUES (?,?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int orderHeaderId, final int itemId, final int categoryId,
			final int referId, final float amount, final int qty) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT_WITH_SET, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, orderHeaderId);
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, referId);
			psmt.setFloat(++i, amount);
			psmt.setInt(++i, qty);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("roomservice_order_line insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_AMOUNT = "SELECT rol_roomservice_item_id,rol_amount,rol_qty FROM roomservice_order_line WHERE "
			+ "rol_roomservice_order_header_id = ?;";

	public List<RoomServiceOrderLineAmountBean> selectAmount(final Connection conn, final int orderId)
			throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceOrderLineAmountBean> list = new ArrayList<RoomServiceOrderLineAmountBean>();
		try {
			psmt = conn.prepareStatement(SELECT_AMOUNT);

			int i = 0;
			psmt.setInt(++i, orderId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RoomServiceOrderLineAmountBean(rs.getInt("rol_roomservice_item_id"),
						rs.getFloat("rol_amount"), rs.getInt("rol_qty")));
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
