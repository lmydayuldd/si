package com.sidc.dao.sits.laundrytype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryTypeBean;

public class LaundryTypeDao {

	private static final class lazyHolder {
		public static LaundryTypeDao INSTANCE = new LaundryTypeDao();
	}

	public static LaundryTypeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_type(lt_status,lt_creation_time) VALUES(?,now()); ";

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
				throw new SQLException("laundry_type insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT lt_id,lt_status FROM laundry_type;";

	public List<LaundryTypeBean> select(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryTypeBean> list = new ArrayList<LaundryTypeBean>();
		try {
			psmt = conn.prepareStatement(SELECT);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryTypeBean(rs.getInt("lt_id"), rs.getInt("lt_status")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_STATUS = "SELECT lt_id,lt_status FROM laundry_type WHERE lt_status = ?;";

	public List<LaundryTypeBean> selectByStatus(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryTypeBean> list = new ArrayList<LaundryTypeBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_STATUS);

			int i = 0;
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryTypeBean(rs.getInt("lt_id"), rs.getInt("lt_status")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE = "UPDATE laundry_type SET lt_status = ? WHERE lt_id = ?;";

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

	private final static String SELECT_BY_ID = "SELECT lt_id FROM laundry_type WHERE lt_id = ?;";

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

	private final static String SELECT_BY_ID_STATUS = "SELECT lt_id FROM laundry_type WHERE lt_id = ? AND lt_status = ?;";

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
