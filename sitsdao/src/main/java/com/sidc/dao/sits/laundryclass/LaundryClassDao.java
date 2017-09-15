package com.sidc.dao.sits.laundryclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.laundry.bean.LaundryClassBean;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class LaundryClassDao {

	private static final class lazyHolder {
		public static LaundryClassDao INSTANCE = new LaundryClassDao();
	}

	public static LaundryClassDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_class(lc_status,lc_service_charge,lc_creation_time) VALUES(?,?,NOW()); ";

	public int insert(final Connection conn, final int status, final float serviceCharge) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setFloat(++i, serviceCharge);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_class insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT lc_id,lc_status,lc_service_charge FROM laundry_class;";

	public List<LaundryClassBean> select(final Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryClassBean> list = new ArrayList<LaundryClassBean>();
		try {
			psmt = conn.prepareStatement(SELECT);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryClassBean(rs.getInt("lc_id"), rs.getInt("lc_status"),
						rs.getFloat("lc_service_charge")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_STATUS = "SELECT lc_id,lc_status,lc_service_charge FROM laundry_class WHERE lc_status = ?;";

	public List<LaundryClassBean> selectByStatus(final Connection conn, final int status) throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryClassBean> list = new ArrayList<LaundryClassBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_STATUS);

			int i = 0;
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LaundryClassBean(rs.getInt("lc_id"), rs.getInt("lc_status"),
						rs.getFloat("lc_service_charge")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE = "UPDATE laundry_class SET lc_status = ?,lc_service_charge = ? WHERE lc_id = ?;";

	public void update(final Connection conn, final int id, final int status, final float serviceCharge)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setFloat(++i, serviceCharge);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ID = "SELECT lc_id FROM laundry_class WHERE lc_id = ? ;";

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

	private final static String SELECT_BY_ID_STATUS = "SELECT lc_id FROM laundry_class WHERE lc_id = ? AND lc_status = ?;";

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

	private final static String SELECT_SERVICE_CHARGE = "SELECT lc_service_charge FROM laundry_class WHERE lc_id = ? AND lc_status = 1;";

	public float selectServiceCharge(final Connection conn, final int id) throws SQLException, SiDCException {

		PreparedStatement psmt = null;
		float serviceCharge = -1;
		try {
			psmt = conn.prepareStatement(SELECT_SERVICE_CHARGE);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				serviceCharge = rs.getFloat("lc_service_charge");
			} else {
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "laundry_class id not exist.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return serviceCharge;
	}

}
