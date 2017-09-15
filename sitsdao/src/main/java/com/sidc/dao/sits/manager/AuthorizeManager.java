package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.userauthorization.bean.AuthorizationFunctionBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.roletofunction.RoleToFuntionDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.systemfunction.SystemFunctionDao;
import com.sidc.dao.sits.systemuser.SystemUserDao;
import com.sidc.dao.sits.tokenheader.TokenHeaderDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;
import com.sidc.utils.status.SiDCStatus;

public class AuthorizeManager {
	private AuthorizeManager() {
	}

	private static class LazyHolder {
		public static final AuthorizeManager INSTANCE = new AuthorizeManager();
	}

	public static AuthorizeManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void mobileAuthorize(final String roomNo, final String token, int type) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!RoomDao.getInstance().isCheckIn(conn, roomNo)) {
				throw new SiDCException(SiDCStatus.ROOM_NOT_CHECK_IN, "Room not check in.");
			}

			if (!TokenHeaderDao.getInstance().checkExpired(conn, token, type, roomNo)) {
				throw new SiDCException(APIStatus.FAIL_AUTHENTICATION, "Auth expired.");
			}
			TokenManager.getInstance().deleteWithExpired(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

	}

	public void mobileAuthorize(final String roomNo, final String token, final String privateKey, int type)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!RoomDao.getInstance().isCheckIn(conn, roomNo)) {
				throw new SiDCException(SiDCStatus.ROOM_NOT_CHECK_IN, "Room not check in.");
			}

			if (!TokenHeaderDao.getInstance().checkExpiredWithKey(conn, token, privateKey, type, roomNo)) {
				throw new SiDCException(APIStatus.FAIL_AUTHENTICATION, "Auth expired.");
			}

			TokenManager.getInstance().deleteWithExpired(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void StaffChatAuthorize(final String token) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!TokenHeaderDao.getInstance().checkTokenExpiredWithChat(conn, token)) {
				throw new SiDCException(APIStatus.FAIL_AUTHENTICATION, "Auth expired.");
			}

			TokenManager.getInstance().deleteWithExpired(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void APIAuthorize(final String token, final String functionId) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!TokenHeaderDao.getInstance().checkTokenExpiredWithChat(conn, token)) {
				throw new SiDCException(APIStatus.FAIL_AUTHENTICATION, "Auth expired.");
			}

			TokenManager.getInstance().deleteWithExpired(conn);

			final String staffId = TokenStaffDetailDao.getInstance().selectStaffIdByToken(conn, token);

			final String roleId = SystemUserDao.getInstance().getRoleId(conn, staffId);

			if (!RoleToFuntionDao.getInstance().isExist(conn, functionId, roleId)) {
				throw new SiDCException(APIStatus.FAIL_OPERATE_PERMISSION, "Can not use this api(no authorize).");
			}

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}	

	public List<String> selectFunctionId(final String token) throws SQLException, SiDCException {

		Connection conn = null;

		List<String> list = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String staffId = TokenStaffDetailDao.getInstance().selectStaffIdByToken(conn, token);

			final String roleId = SystemUserDao.getInstance().getRoleId(conn, staffId);

			list = RoleToFuntionDao.getInstance().listFunctionId(conn, roleId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<AuthorizationFunctionBean> selectFunction(final List<String> codeList)
			throws SQLException, SiDCException {

		Connection conn = null;

		List<AuthorizationFunctionBean> list = new ArrayList<AuthorizationFunctionBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String code : codeList) {
				list.addAll(SystemFunctionDao.getInstance().select(conn, code));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}
}
