package com.sidc.dao.sits.laundryorderline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryOrderLineBean;

public class LaundryOrderLineDao {

	private static final class lazyHolder {
		public static LaundryOrderLineDao INSTANCE = new LaundryOrderLineDao();
	}

	public static LaundryOrderLineDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_order_line(lol_laundry_header_id,lol_type_id,lol_item_id,"
			+ "lol_wash_type_id,lol_return_type_id,"
			+ "lol_piece,lol_price,lol_creation_time) VALUES(?,?,?,?,?,?,?,NOW()); ";

	public int insert(final Connection conn, final int laundryHeaderId, final int typeId, final int itemId,
			final int washTypeId, final int returnTypeId, final int piece, final float price) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, laundryHeaderId);
			psmt.setInt(++i, typeId);
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, washTypeId);
			psmt.setInt(++i, returnTypeId);
			psmt.setInt(++i, piece);
			psmt.setFloat(++i, price);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_order_line insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String INSERT_DEPICTION = "INSERT INTO laundry_order_line(lol_laundry_header_id,lol_type_id,"
			+ "lol_item_id,lol_wash_type_id,lol_piece," + "lol_price,lol_creation_time) VALUES(?,?,?,?,?,?,NOW()); ";

	public int insert(final Connection conn, final int laundryHeaderId, final int typeId, final int itemId,
			final int washTypeId, final int piece, final float price) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT_DEPICTION, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, laundryHeaderId);
			psmt.setInt(++i, typeId);
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, washTypeId);
			psmt.setInt(++i, piece);
			psmt.setFloat(++i, price);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_order_line insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT_INFO = "SELECT lol_type_id,lol_item_id,lol_wash_type_id,lol_return_type_id,lol_piece,lol_price,"
			+ "DATE_FORMAT(lol_creation_time,'%Y/%m/%d %H:%i')AS lol_creation_time ,"
			+ "(SELECT lil_name FROM laundry_item_lang WHERE lol_item_id = lil_laundry_item_id AND lil_lang = ? LIMIT 1) AS item_name,"
			+ "(SELECT ltl_name FROM laundry_type_lang WHERE lol_type_id = ltl_lanudry_type_id AND ltl_lang = ? LIMIT 1) AS type_name,"
			+ "(SELECT lwtl_name FROM laundry_wash_type_lang WHERE lol_wash_type_id = lwtl_laundry_wash_type_id AND lwtl_lang = ? "
			+ "LIMIT 1) AS wash_type_name,(SELECT lrtl_name FROM laundry_return_type_lang WHERE lol_return_type_id = "
			+ "lrtl_return_type_id AND lrtl_lang = ?  LIMIT 1) AS return_type_name FROM laundry_order_line WHERE lol_laundry_header_id = ?";

	public List<LaundryOrderLineBean> selectInfo(final Connection conn, final int orderId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryOrderLineBean> list = new ArrayList<LaundryOrderLineBean>();
		try {
			psmt = conn.prepareStatement(SELECT_INFO);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setString(++i, langCode);
			psmt.setString(++i, langCode);
			psmt.setString(++i, langCode);
			psmt.setInt(++i, orderId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryOrderLineBean(rs.getInt("lol_type_id"), rs.getString("type_name"),
						rs.getInt("lol_item_id"), rs.getString("item_name"), rs.getInt("lol_wash_type_id"),
						rs.getString("wash_type_name"), rs.getInt("lol_return_type_id"),
						rs.getString("return_type_name"), rs.getInt("lol_piece"), rs.getFloat("lol_price")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_PRICE = "SELECT lol_type_id,lol_item_id,lol_price,lol_piece"
			+ " FROM laundry_order_line WHERE lol_laundry_header_id = ?";

	public List<LaundryOrderLineBean> selectItemPrice(final Connection conn, final int orderId) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryOrderLineBean> list = new ArrayList<LaundryOrderLineBean>();
		try {
			psmt = conn.prepareStatement(SELECT_PRICE);

			int i = 0;
			psmt.setInt(++i, orderId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryOrderLineBean(rs.getInt("lol_type_id"), rs.getInt("lol_item_id"),
						rs.getInt("lol_piece"), rs.getFloat("lol_price")));
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
