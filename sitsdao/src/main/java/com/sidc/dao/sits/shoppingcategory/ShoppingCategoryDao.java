package com.sidc.dao.sits.shoppingcategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.response.ShoppingCategoryResponse;

public class ShoppingCategoryDao {
	/**
	 * @author Joe
	 */
	private ShoppingCategoryDao() {
	}

	private static class LazyHolder {
		public static final ShoppingCategoryDao INSTANCE = new ShoppingCategoryDao();
	}

	public static ShoppingCategoryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO shopping_category(sc_status,sc_sequence,sc_refer_id,sc_modify_time,sc_creation_time) "
			+ "VALUES (?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int status, final int referId, final int sequence)
			throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, sequence);
			psmt.setInt(++i, referId);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("shopping_category insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE shopping_category SET sc_status = ?,sc_sequence = ?,sc_refer_id=?,sc_modify_time = NOW() "
			+ "WHERE sc_id = ? ; ";

	public void update(final Connection conn, final int categoryId, final int status, final int referId,
			final int sequence) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, referId);
			psmt.setInt(++i, sequence);
			psmt.setInt(++i, categoryId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_REFERID = "UPDATE shopping_category SET sc_refer_id = ?,sc_modify_time = NOW() WHERE sc_id = ? ;";

	public void updateReferId(final Connection conn, final int scId, final int referId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_REFERID);

			int i = 0;
			psmt.setInt(++i, referId);
			psmt.setInt(++i, scId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT sc_id FROM shopping_category WHERE sc_id = ?";

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

	private final static String IS_EXIST_BY_REFERID = "SELECT sc_refer_id FROM shopping_category WHERE sc_refer_id = ?";

	public boolean isExistByReferId(final Connection conn, final int referId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST_BY_REFERID);

			int i = 0;
			psmt.setInt(++i, referId);

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

	private final static String SELECT = "SELECT sc_id,sc_status,sc_sequence,sc_refer_id,DATE_FORMAT(sc_creation_time,'%Y/%m/%d %H:%i')AS sc_creation_time FROM shopping_category ";

	public List<ShoppingCategoryResponse> selectCategory(final Connection conn, final int categoryId, final int status,
			final int referId) throws SQLException {

		PreparedStatement psmt = null;
		List<ShoppingCategoryResponse> list = new ArrayList<ShoppingCategoryResponse>();
		try {
			String parameter = "";
			int x = 0;
			if (categoryId > 0) {
				parameter = formatWhereDescription(x++, " sc_id = " + categoryId, parameter);
			}
			if (status >= 0) {
				parameter = formatWhereDescription(x++, " sc_status = " + status, parameter);
			}
			if (referId > 0) {
//				parameter = formatWhereDescription(x++, " sc_id = " + referId, parameter);
				parameter = formatWhereDescription(x++, " sc_refer_id = " + referId, parameter);
			}

			if (StringUtils.isBlank(parameter)) {
				psmt = conn.prepareStatement(SELECT);
			} else {
				psmt = conn.prepareStatement(SELECT + parameter);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ShoppingCategoryResponse(rs.getInt("sc_id"), rs.getInt("sc_status"),
						rs.getInt("sc_refer_id"), rs.getInt("sc_sequence"), rs.getString("sc_creation_time")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECTE_BY_REDERID = "SELECT sc_id,sc_status,sc_sequence,sc_refer_id,DATE_FORMAT(sc_creation_time,"
			+ "'%Y/%m/%d %H:%i')AS sc_creation_time FROM shopping_category WHERE sc_refer_id = ? AND sc_id != sc_refer_id";

	public List<ShoppingCategoryResponse> selectByReferId(final Connection conn, final int referId)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ShoppingCategoryResponse> list = new ArrayList<ShoppingCategoryResponse>();
		try {

			int i = 0;

			psmt = conn.prepareStatement(SELECTE_BY_REDERID);
			psmt.setInt(++i, referId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ShoppingCategoryResponse(rs.getInt("sc_id"), rs.getInt("sc_status"),
						rs.getInt("sc_refer_id"), rs.getInt("sc_sequence"), rs.getString("sc_creation_time")));
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
