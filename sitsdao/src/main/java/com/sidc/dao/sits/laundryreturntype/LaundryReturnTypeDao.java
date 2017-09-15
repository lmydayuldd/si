package com.sidc.dao.sits.laundryreturntype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryReturnTypeBean;

public class LaundryReturnTypeDao {

	private static final class lazyHolder {
		public static LaundryReturnTypeDao INSTANCE = new LaundryReturnTypeDao();
	}

	public static LaundryReturnTypeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_return_type(lry_status,lry_creation_time) VALUES(?,now()); ";

	public int insert(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, status);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_return_type insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT lry_id,lry_status FROM laundry_return_type;";

	public List<LaundryReturnTypeBean> select(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryReturnTypeBean> list = new ArrayList<LaundryReturnTypeBean>();
		try {
			psmt = conn.prepareStatement(SELECT);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryReturnTypeBean(rs.getInt("lry_id"), rs.getInt("lry_status")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_STATUS = "SELECT lry_id,lry_status FROM laundry_return_type WHERE lry_status = ?;";

	public List<LaundryReturnTypeBean> selectByStatus(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryReturnTypeBean> list = new ArrayList<LaundryReturnTypeBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_STATUS);

			int i = 0;
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryReturnTypeBean(rs.getInt("lry_id"), rs.getInt("lry_status")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE = "UPDATE laundry_return_type SET lry_status = ? WHERE lry_id = ?;";

	public void update(final Connection conn, final int id, final int status) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ID = "SELECT lry_id FROM laundry_return_type WHERE lry_id = ?;";

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

	private final static String SELECT_BY_ID_STATUS = "SELECT lry_id FROM laundry_return_type WHERE lry_id = ? AND lry_status = ?;";

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

}
