package com.sidc.dao.sits.spareitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.spare.response.SpareItemResponse;

public class SpareItemDao {
	/**
	 * @author Joe
	 */
	private SpareItemDao() {
	}

	private static class LazyHolder {
		public static final SpareItemDao INSTANCE = new SpareItemDao();
	}

	public static SpareItemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO spare_item(si_spare_category_id,si_status,si_sequence"
			+ ",si_price,si_qty,si_modify_time,si_creation_time)VALUES (?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int categoryId, final String status, final int sequence,
			final float price, final int qty) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setString(++i, status);
			psmt.setInt(++i, sequence);
			psmt.setFloat(++i, price);
			psmt.setInt(++i, qty);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("spare_item insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE spare_item SET si_spare_category_id = ?,si_status=?,"
			+ "si_sequence=?,si_price=?,si_qty=?,si_modify_time = NOW() WHERE si_id = ? ; ";

	public void update(final Connection conn, final int id, final int categoryId, final String status,
			final int sequence, final float price, final int qty) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setString(++i, status);
			psmt.setInt(++i, sequence);
			psmt.setFloat(++i, price);
			psmt.setInt(++i, qty);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_QTY = "UPDATE spare_item SET si_qty = ?,si_modify_time = NOW() WHERE si_id = ? ;";

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

	private final static String UPDATE_STATUS = "UPDATE spare_item SET si_status = ?,si_modify_time = NOW() WHERE si_id = ? ;";

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

	private final static String IS_EXIST = "SELECT si_id FROM spare_item WHERE si_id = ?";

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

	private final static String IS_EXIST_BY_STATUS = "SELECT si_id FROM spare_item WHERE si_id = ? AND si_status = ?";

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

	private final static String SELECT = "SELECT si_id,si_spare_category_id,si_status,"
			+ "si_price,si_qty,si_sequence,DATE_FORMAT(si_creation_time,'%Y/%m/%d %H:%i')AS si_creation_time FROM spare_item ";

	public List<SpareItemResponse> select(final Connection conn, final int itemId, final int categoryId,
			final String status) throws SQLException {

		PreparedStatement psmt = null;
		List<SpareItemResponse> list = new ArrayList<SpareItemResponse>();
		try {
			String parameter = "WHERE";
			int x = 0;
			if (itemId > 0) {
				parameter = formatWhereDescription(x++, " si_id = " + itemId, parameter);
			}
			if (categoryId > 0) {
				parameter = formatWhereDescription(x++, " si_spare_category_id = " + categoryId, parameter);
			}
			if (!StringUtils.isBlank(status)) {
				parameter = formatWhereDescription(x++, " si_status = '" + status + "'", parameter);
			}

			if (parameter.equals("WHERE")) {
				parameter = "";
			}

			psmt = conn.prepareStatement(SELECT + parameter);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new SpareItemResponse(rs.getInt("si_id"), rs.getInt("si_spare_category_id"),
						rs.getString("si_status"), rs.getInt("si_sequence"), rs.getFloat("si_price"),
						rs.getInt("si_qty"), rs.getString("si_creation_time")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT si_id,si_spare_category_id,si_status,"
			+ "si_price,si_qty,si_sequence,DATE_FORMAT(si_creation_time,'%Y/%m/%d %H:%i')AS si_creation_time FROM spare_item "
			+ "WHERE si_id = ? ";

	public SpareItemResponse select(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		SpareItemResponse entity = null;

		try {
			int i = 0;

			psmt = conn.prepareStatement(SELECT_BY_ID);
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new SpareItemResponse(rs.getInt("si_id"), rs.getInt("si_spare_category_id"),
						rs.getString("si_status"), rs.getInt("si_sequence"), rs.getFloat("si_price"),
						rs.getInt("si_qty"), rs.getString("si_creation_time"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private String formatWhereDescription(int i, final String sqlWhereDescription, String description) {
		if (i == 0) {
			description += sqlWhereDescription;
		} else {
			description += " AND" + sqlWhereDescription;
		}
		return description;
	}

}
