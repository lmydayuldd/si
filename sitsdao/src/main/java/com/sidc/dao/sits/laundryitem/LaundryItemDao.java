package com.sidc.dao.sits.laundryitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemBean;

public class LaundryItemDao {

	private static final class lazyHolder {
		public static LaundryItemDao INSTANCE = new LaundryItemDao();
	}

	public static LaundryItemDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_item(li_laundry_type_id,li_status,li_creation_time)"
			+ " VALUES(?,?,NOW()); ";

	public int insert(final Connection conn, final int typeId, final int status) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, typeId);
			psmt.setInt(++i, status);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_item insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT li_id,li_status,li_laundry_type_id FROM laundry_item;";

	public List<LaundryItemBean> select(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryItemBean> list = new ArrayList<LaundryItemBean>();
		try {
			psmt = conn.prepareStatement(SELECT);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryItemBean(rs.getInt("li_id"), rs.getInt("li_status"),
						rs.getInt("li_laundry_type_id")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_STATUS = "SELECT li_id,li_laundry_type_id FROM laundry_item WHERE li_status = ?;";

	public List<LaundryItemBean> selectByStatus(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryItemBean> list = new ArrayList<LaundryItemBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_STATUS);

			int i = 0;
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new LaundryItemBean(rs.getInt("li_id"), status, rs.getInt("li_laundry_type_id")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE = "UPDATE laundry_item SET li_status = ?,li_laundry_type_id = ? WHERE li_id = ?;";

	public void update(final Connection conn, final int id, final int status, final int typeId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, typeId);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ID = "SELECT li_id FROM laundry_item WHERE li_id = ?;";

	public boolean isExist(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, id);

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

	private final static String SELECT_BY_ID_STATUS = "SELECT li_id,li_status,li_laundry_type_id FROM laundry_item WHERE li_id = ? "
			+ "AND li_status = ?;";

	public boolean isExist(final Connection conn, final int id, final int status) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_STATUS);

			int i = 0;
			psmt.setInt(++i, id);
			psmt.setInt(++i, status);
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

	public LaundryItemBean selectByEnable(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		LaundryItemBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID_STATUS);

			int i = 0;
			psmt.setInt(++i, id);
			psmt.setInt(++i, 1);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new LaundryItemBean(id, rs.getInt("li_status"), rs.getInt("li_laundry_type_id"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}
}
