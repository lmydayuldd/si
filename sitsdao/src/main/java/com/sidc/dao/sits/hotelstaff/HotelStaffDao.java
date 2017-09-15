package com.sidc.dao.sits.hotelstaff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.sidc.blackcore.api.sits.account.bean.SaffInfoBean;

public class HotelStaffDao {
	/**
	 * @author Joe
	 */
	private HotelStaffDao() {
	}

	private static class LazyHolder {
		public static final HotelStaffDao INSTANCE = new HotelStaffDao();
	}

	public static HotelStaffDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SEARCH_BY_CODE = "SELECT staff_name FROM hotel_staff WHERE staff_code = ? LIMIT 1;";

	public String seleceNameByCode(final Connection conn, final String staffCode) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BY_CODE);

			int i = 0;
			psmt.setString(++i, staffCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("staff_name");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

	private final static String SEARCH_BY_ID = "SELECT staff_name FROM hotel_staff WHERE staff_system_user_id = ? LIMIT 1;";

	public String seleceNameById(final Connection conn, final String id) throws SQLException {

		PreparedStatement psmt = null;
		String name = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BY_ID);

			int i = 0;
			psmt.setString(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("staff_name");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return name;
	}

	private final static String SELECT_ID = "SELECT uuid FROM hotel_staff WHERE staff_code = ? LIMIT 1;";

	public String seleceId(final Connection conn, final String staffCode) throws SQLException {

		PreparedStatement psmt = null;
		String id = null;
		try {
			psmt = conn.prepareStatement(SELECT_ID);

			int i = 0;
			psmt.setString(++i, staffCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getString("uuid");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String IS_EXIST = "SELECT uuid FROM hotel_staff WHERE uuid = ? ;";

	public boolean isExist(final Connection conn, final String id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setString(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String UPDATA_MOBILEINFO = "UPDATE hotel_staff SET staff_mobile_info_id = ? WHERE uuid = ? ;";

	public void updateMobileInfo(final Connection conn, final int mobileInfoId, final String staffId)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATA_MOBILEINFO);

			int i = 0;
			psmt.setInt(++i, mobileInfoId);
			psmt.setString(++i, staffId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_STAFFINFO = "SELECT hs.staff_system_user_id,hs.staff_code,hs.staff_name,mi.mi_push_token FROM hotel_staff hs "
			+ "LEFT JOIN mobile_info mi ON hs.staff_mobile_info_id = mi.mi_id WHERE staff_system_user_id = ? ;";

	public SaffInfoBean selectStaffInfo(final Connection conn, final String id) throws SQLException {

		PreparedStatement psmt = null;
		SaffInfoBean entity = null;

		try {
			psmt = conn.prepareStatement(SELECT_STAFFINFO);

			int i = 0;
			psmt.setString(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				entity = new SaffInfoBean(rs.getString("staff_system_user_id"), rs.getString("staff_code"), rs.getString("staff_name"),
						null);
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String INSERT = "INSERT INTO hotel_staff(uuid,staff_code,staff_name,staff_system_user_id)VALUES (?,?,?,?)";

	public void insert(final Connection conn, final String staffCode, final String name, final String systemUserId)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, UUID.randomUUID().toString().replace("-", ""));
			psmt.setString(++i, staffCode);
			psmt.setString(++i, name);
			psmt.setString(++i, systemUserId);

			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

}
