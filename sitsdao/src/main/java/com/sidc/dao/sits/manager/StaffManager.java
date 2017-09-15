package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sidc.blackcore.api.sits.account.bean.SaffInfoBean;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.hotelstaff.HotelStaffDao;
import com.sidc.dao.sits.photo.PhotoDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;

public class StaffManager {
	private StaffManager() {
	}

	private static class LazyHolder {
		public static final StaffManager INSTANCE = new StaffManager();
	}

	public static StaffManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getStaffNameById(final String id) throws SQLException {

		Connection conn = null;

		String name = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			name = HotelStaffDao.getInstance().seleceNameById(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return name;
	}

	public String getStaffNameByCode(final String staffCode) throws SQLException {

		Connection conn = null;

		String name = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			name = HotelStaffDao.getInstance().seleceNameByCode(conn, staffCode);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return name;
	}

	public String getStaffIdByCode(final String staffCode) throws SQLException {

		Connection conn = null;

		String id = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = HotelStaffDao.getInstance().seleceId(conn, staffCode);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return id;
	}

	public boolean checkId(final String id) throws SQLException {

		Connection conn = null;

		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = HotelStaffDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public SaffInfoBean getStaffInfoWithTokenInfo(final String token, final String id) throws SQLException {

		Connection conn = null;

		SaffInfoBean entity = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// final String staffId =
			// TokenStaffDetailDao.getInstance().selectStaffIdByToken(conn,
			// token);

			entity = HotelStaffDao.getInstance().selectStaffInfo(conn, id);

			entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, id, PhotoType.STAFF.toString()));

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}

	public boolean isExist(final String token, final String id) throws SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String staffId = TokenStaffDetailDao.getInstance().selectStaffIdByToken(conn, token);

			if (id.equals(staffId)) {
				isExist = true;
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isExist;
	}
}
