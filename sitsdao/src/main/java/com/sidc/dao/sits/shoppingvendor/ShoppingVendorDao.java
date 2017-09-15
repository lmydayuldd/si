package com.sidc.dao.sits.shoppingvendor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.response.ShoppingVendorResponse;

public class ShoppingVendorDao {
	/**
	 * @author Joe
	 */
	private ShoppingVendorDao() {
	}

	private static class LazyHolder {
		public static final ShoppingVendorDao INSTANCE = new ShoppingVendorDao();
	}

	public static ShoppingVendorDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO shopping_vendor(sv_status,sv_tex,sv_email,sv_address,sv_modify_time,sv_creation_time) "
			+ "VALUES (?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int status, final String tex, final String email,
			final String address) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, status);
			psmt.setString(++i, tex);
			psmt.setString(++i, email);
			psmt.setString(++i, address);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("shopping_vendor insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE shopping_vendor SET sv_status = ?,sv_tex = ?,sv_email=?,sv_address=?,sv_modify_time = NOW() "
			+ "WHERE sv_id = ? ; ";

	public void update(final Connection conn, final int id, final int status, final String tex, final String email,
			final String address) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setString(++i, tex);
			psmt.setString(++i, email);
			psmt.setString(++i, address);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT sv_id FROM shopping_vendor WHERE sv_id = ?";

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

	private final static String SELECT = "SELECT sv_id,sv_status,sv_tex,sv_email,sv_address,"
			+ "DATE_FORMAT(sv_creation_time,'%Y/%m/%d %H:%i')AS sv_creation_time FROM shopping_vendor ";

	public List<ShoppingVendorResponse> selectVendor(final Connection conn, final int vendorId, final int status)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ShoppingVendorResponse> list = new ArrayList<ShoppingVendorResponse>();
		try {
			String parameter = "";
			int x = 0;
			if (vendorId > 0) {
				parameter = formatWhereDescription(x++, " sv_id = " + vendorId, parameter);
			}
			if (status >= 0) {
				parameter = formatWhereDescription(x++, " sv_status = " + status, parameter);
			}
			if (StringUtils.isBlank(parameter)) {
				psmt = conn.prepareStatement(SELECT);
			} else {
				psmt = conn.prepareStatement(SELECT + parameter);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ShoppingVendorResponse(rs.getInt("sv_id"), rs.getString("sv_status"),
						rs.getString("sv_tex"), rs.getString("sv_email"), rs.getString("sv_address"),
						rs.getString("sv_creation_time")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_NAME = "SELECT sv_id,sv_status,sv_tex,sv_email,sv_address,"
			+ "DATE_FORMAT(sv_creation_time,'%Y/%m/%d %H:%i')AS sv_creation_time FROM shopping_vendor LEFT JOIN shopping_vendor_lang "
			+ "ON sv_id = svl_shopping_vendor_id WHERE svl_lang = ? AND svl_name like ? ";

	public List<ShoppingVendorResponse> selectVendor(final Connection conn, final int vendorId, final int status,
			final String langCode, final String vendorName) throws SQLException {

		PreparedStatement psmt = null;
		List<ShoppingVendorResponse> list = new ArrayList<ShoppingVendorResponse>();
		try {
			String parameter = "";

			if (vendorId > 0) {
				parameter += " AND sv_id = " + vendorId;
			}
			if (status >= 0) {
				parameter += " AND sv_status = " + status;
			}

			if (StringUtils.isBlank(parameter)) {
				psmt = conn.prepareStatement(SELECT_BY_NAME);
			} else {
				psmt = conn.prepareStatement(SELECT_BY_NAME + parameter);
			}

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setString(++i, "%" + vendorName + "%");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ShoppingVendorResponse(rs.getInt("sv_id"), rs.getString("sv_status"),
						rs.getString("sv_tex"), rs.getString("sv_email"), rs.getString("sv_address"),
						rs.getString("sv_creation_time")));
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
