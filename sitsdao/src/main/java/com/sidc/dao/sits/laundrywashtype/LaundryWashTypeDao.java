package com.sidc.dao.sits.laundrywashtype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryWashTypeStatusBean;

public class LaundryWashTypeDao {

	private static final class lazyHolder {
		public static LaundryWashTypeDao INSTANCE = new LaundryWashTypeDao();
	}

	public static LaundryWashTypeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_wash_type(lwt_status,lwt_creation_time) VALUES(?,now()); ";

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
				throw new SQLException("laundry_wash_type insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT lwt_id,lwt_status FROM laundry_wash_type;";

	public List<LaundryWashTypeStatusBean> select(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryWashTypeStatusBean> list = new ArrayList<LaundryWashTypeStatusBean>();
		try {
			psmt = conn.prepareStatement(SELECT);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryWashTypeStatusBean(rs.getInt("lwt_id"), rs.getInt("lwt_status")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_STATUS = "SELECT lwt_id,lwt_status FROM laundry_wash_type WHERE lwt_status = ?;";

	public List<LaundryWashTypeStatusBean> selectByStatus(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryWashTypeStatusBean> list = new ArrayList<LaundryWashTypeStatusBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_STATUS);

			int i = 0;
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryWashTypeStatusBean(rs.getInt("lwt_id"), rs.getInt("lwt_status")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE = "UPDATE laundry_wash_type SET lwt_status = ? WHERE lwt_id = ?;";

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

	private final static String SELECT_BY_ID = "SELECT lwt_id FROM laundry_wash_type WHERE lwt_id = ?;";

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

	private final static String SELECT_BY_ID_STATUS = "SELECT lwt_id FROM laundry_wash_type WHERE lwt_id = ? AND lwt_status = ?;";

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
